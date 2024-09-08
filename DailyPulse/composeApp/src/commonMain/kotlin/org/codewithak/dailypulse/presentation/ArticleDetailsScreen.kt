package org.codewithak.dailypulse.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import org.codewithak.dailypulse.domain.model.ArticlesObject

data class ArticleDetailsScreen (val articleClicked: ArticlesObject):Screen{

    @Composable
    override fun Content() {
        //Back button
        val navigator= LocalNavigator.currentOrThrow

   Scaffold(
       topBar = {
           Text(
               "Complete Story",
               color= Color.Black,
               fontWeight = FontWeight.Bold,
               fontSize = 30.sp,
               modifier = Modifier.padding(top=90.dp,start=16.dp),

           )
       },
       modifier = Modifier.fillMaxSize(),
   ){
       Column (
           modifier = Modifier.padding(it)
       ){
           Card(
               modifier=Modifier.fillMaxWidth().height(301.dp).padding(17.dp),
               shape= RoundedCornerShape(20.dp),
           ){
              articleClicked.urlImage?.let{
                  AsyncImage(
                      modifier = Modifier
                          .fillMaxSize().clip(shape=RoundedCornerShape(20.dp)),
                      model =articleClicked.urlImage,
                      contentScale=ContentScale.Crop,
                      contentDescription=null
                  )
              }
           }

           articleClicked.author?.let{it1 ->
               Text(
                   text =it1,
                   color=Color.Gray,
                   fontWeight = FontWeight.Bold,
                   fontSize=12.sp,
                   modifier=Modifier.padding(top=20.dp,start=16.dp,end=16.dp),
               )
           }
           articleClicked.title?.let{it1 ->
               Text(
                   text=it1,
                   color=Color.Black,
                   fontWeight = FontWeight.Medium,
                   fontSize = 17.sp,
                   modifier=Modifier.padding(top=20.dp,start=16.dp,end=16.dp),
                   maxLines = 3,
                   overflow = TextOverflow.Ellipsis,
               )
           }
           articleClicked.content?.let{it1 ->
               Text(
                   text = it1,
                   color=Color.Black,
                   fontWeight = FontWeight.Normal,
                   fontSize = 14.sp,
                   modifier = Modifier.padding(top=20.dp,start=16.dp,end=16.dp),
                   overflow = TextOverflow.Ellipsis,
               )
           }

           Column (
               modifier = Modifier.fillMaxWidth().fillMaxHeight(),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally
           ){
               Button(
                   onClick={navigator.pop()},
                   colors=ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                   contentPadding= PaddingValues(16.dp),
               ){
                   Text("Black",color=Color.White)
               }

           }

       }
   }
    }

}