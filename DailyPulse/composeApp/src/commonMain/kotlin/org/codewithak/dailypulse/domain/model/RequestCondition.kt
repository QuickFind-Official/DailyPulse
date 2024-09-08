package org.codewithak.dailypulse.domain.model

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

sealed class RequestCondition<out T> {
    data object IdleCondition : RequestCondition<Nothing>()
    data object  LoadingCondition : RequestCondition<Nothing>()
    data class SuccessCondition<out T>(val data: T) : RequestCondition<T>()
    data class ErrorCondition(val message:String) : RequestCondition<Nothing>()

    fun isLoading(): Boolean = this is LoadingCondition
    fun isError(): Boolean = this is ErrorCondition

    fun isSuccess(): Boolean = this is SuccessCondition

    fun getSuccessDataInfo() = (this as SuccessCondition).data
    fun getErrorMessageInfo() = (this as ErrorCondition).message
}

@Composable
fun <T> RequestCondition<T>.ShowResult(
    onIdle: (@Composable () -> Unit)? = null,
    onLoading: (@Composable () -> Unit)? = null,
    onError: (@Composable (String) -> Unit)? = null,
    onSuccess: @Composable (T) -> Unit,
    transitionSpec: ContentTransform = scaleIn(animationSpec = tween(durationMillis = 450)) +
            fadeIn(animationSpec = tween(durationMillis = 850)) togetherWith
            scaleOut(animationSpec = tween(durationMillis = 450)) +
            fadeOut(animationSpec = tween(durationMillis = 850))
) {
    AnimatedContent(
        targetState = this,
        transitionSpec = { transitionSpec },
        label = "Content Animation"
    ){

        state ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ){
            when (state){
                is RequestCondition.ErrorCondition -> {
                    onError?.invoke(state.getErrorMessageInfo())
                }
                is RequestCondition.IdleCondition -> {
                    onIdle?.invoke()
                }
                is RequestCondition.LoadingCondition -> {
                 onLoading?.invoke()
                }
                is RequestCondition.SuccessCondition -> {
                   onSuccess(state.getSuccessDataInfo())
                }
            }
        }
    }
}
