package org.codewithak.dailypulse.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.codewithak.dailypulse.domain.model.ArticlesObject


@Composable
fun NewsArticleItem(articleNews: ArticlesObject, onArticleItemClick:(ArticlesObject) -> Unit){

    Card(
        modifier = Modifier.fillMaxWidth().padding(18.dp),
        shape = RoundedCornerShape(8.dp),
    ){
        Row(
            modifier = Modifier.clickable {
              onArticleItemClick(articleNews)
            }
        ){
            articleNews.urlImage?.let{
                AsyncImage(
                    modifier = Modifier
                        .size(121.dp).clip(shape = RoundedCornerShape(11.dp)),
                    model=articleNews.urlImage,
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
            articleNews.title?.let{
                Text(
                    it,
                    maxLines= 3,
                    fontSize= 17.sp,
                    overflow=TextOverflow.Ellipsis,
                    modifier=Modifier.padding(
                        top= 9.dp,
                        start= 13.dp,
                        end= 13.dp,
                        bottom= 9.dp
                    ),
                    color= Color.Black
                )
            }
        }
    }
}
