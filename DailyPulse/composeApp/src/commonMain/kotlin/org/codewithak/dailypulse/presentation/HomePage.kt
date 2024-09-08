package org.codewithak.dailypulse.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.codewithak.dailypulse.domain.model.RequestCondition

class HomePage : Screen{

    @Composable
    override fun Content() {
    val navigator = LocalNavigator.currentOrThrow
        val newsViewModel = getScreenModel<NewsViewModel>()

        val overallNews = newsViewModel.allNews

        Scaffold(
         contentColor = Color.Gray,
         topBar = {
             Text(
                 "HeadLines",
                 color = Color.Black,
                 fontWeight = FontWeight.Bold,
                 fontSize = 30.sp,
                 modifier = Modifier.padding(top=90.dp, start= 16.dp),

             )
         }
        ){
         Column(
            modifier = Modifier.fillMaxSize()
                .padding(top=it.calculateTopPadding(),bottom=it.calculateBottomPadding()),
             horizontalAlignment = Alignment.CenterHorizontally,
             verticalArrangement = Arrangement.Center,
         ){

             when(overallNews.value){

                 is RequestCondition.ErrorCondition -> {
                     val errorMsg = overallNews.value.getSuccessDataInfo()?.toString() ?: "Unknown error"
                     Box(
                         modifier = Modifier.fillMaxSize(),
                         contentAlignment = Alignment.Center
                     ) {
                         Text(
                             text = errorMsg,
                             color = Color.Black
                         )
                     }
                 }

                 RequestCondition.IdleCondition -> {
                     Box(
                         modifier = Modifier.fillMaxSize(),
                         contentAlignment = Alignment.Center
                     ){
                         Text(
                             text="Connection is Idle",
                             color = Color.Black
                         )
                     }
                 }
                 RequestCondition.LoadingCondition -> {
                     Box(
                         modifier = Modifier.fillMaxSize(),
                         contentAlignment = Alignment.Center
                     ){
                        // Animation Indicator yaha pe daal sakte.
                         AnimatingCircleWithArcsProgress(
                             activeColor = Color.Blue, // You can change this color to match your theme
                             modifier = Modifier.fillMaxSize()
                         )
                     }
                 }

                 is RequestCondition.SuccessCondition -> {
                  val allArticles = overallNews.value.getSuccessDataInfo()

                  LazyColumn {
                      items(allArticles){
                          it?.let{
                          NewsArticleItem(it){
                              navigator.push(ArticleDetailsScreen(it))
                          }
                          }
                      }
                  }
                 }
             }
         }
        }
    }

}