package org.codewithak.dailypulse.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.codewithak.dailypulse.domain.model.ArticlesObject
import org.codewithak.dailypulse.domain.model.RequestCondition
import org.codewithak.dailypulse.domain.remote.NewsApiService

class NewsViewModel (private val repoNews: NewsApiService):ScreenModel{

private val _allNews: MutableState<RequestCondition<List<ArticlesObject?>>> = mutableStateOf(RequestCondition.IdleCondition)
    val allNews: State<RequestCondition<List<ArticlesObject?>>> = _allNews

  init {
      retrieveNews()
  }

   private fun retrieveNews(){
       screenModelScope.launch {
           repoNews.readTopHeadline().let {
               it.collectLatest {
                  _allNews.value = it
               }
           }
       }
   }
}