package org.codewithak.dailypulse.domain.remote

import kotlinx.coroutines.flow.Flow
import org.codewithak.dailypulse.domain.model.ArticlesObject
import org.codewithak.dailypulse.domain.model.RequestCondition

interface NewsApiService {
    suspend fun readTopHeadline(): Flow<RequestCondition<List<ArticlesObject?>>>

}