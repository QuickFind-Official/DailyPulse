package org.codewithak.dailypulse.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import org.codewithak.dailypulse.domain.model.ArticlesObject
import org.codewithak.dailypulse.domain.model.MainResponseApiNews
import org.codewithak.dailypulse.domain.model.RequestCondition
import org.codewithak.dailypulse.domain.remote.NewsApiService

class NewsApiServiceImpl: NewsApiService {

    companion object{
        const val BASE_URL_NEWS_API = "https://newsapi.org/v2/top-headlines?sources=techcrunch"
        const val NEWS_API_KEY = "abd25501ee084d31a316f12265448cbb"
    }

    private val httpClient = HttpClient {
        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
        install(HttpTimeout){
            requestTimeoutMillis = 16000
        }
        install(DefaultRequest){
            headers {
                append("X-Api-Key", NEWS_API_KEY)
            }
        }
    }
    override suspend fun readTopHeadline(): Flow<RequestCondition<List<ArticlesObject?>>> {
     return flow {
         try {
          emit(RequestCondition.LoadingCondition)
           val responseFromAPI= httpClient.get(BASE_URL_NEWS_API)

           if(responseFromAPI.status.value == 200) {
               val json = Json { ignoreUnknownKeys = true }

               val apiResponseBody = json.decodeFromString<MainResponseApiNews>(responseFromAPI.body())

               println("Response from API ="+apiResponseBody)

               val overallArticles = apiResponseBody.articles
               emit(RequestCondition.SuccessCondition(overallArticles))
           }
         }
         catch (errorMsg:Exception){
             println("Response from API ="+errorMsg.message.toString())
         emit(RequestCondition.ErrorCondition(errorMsg.message.toString()))
         }
     }
    }
}