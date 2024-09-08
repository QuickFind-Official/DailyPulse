package org.codewithak.dailypulse.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainResponseApiNews(
    val status:String?,
    val totalResults:Int?,
    val articles:List<ArticlesObject?>
)

@Serializable
data class ArticlesObject(
    val source:SourceObject?,
    val author:String?,
    val title:String?,
    val description:String?,
    @SerialName("urlToImage")
    val urlImage:String?,
    val publishedAt:String?,
    val content:String?
)

@Serializable
data class SourceObject(
    val id:String?,
    val name:String?
)