package org.codewithak.dailypulse.dependencyInjection

import org.codewithak.dailypulse.data.NewsApiServiceImpl
import org.codewithak.dailypulse.domain.remote.NewsApiService
import org.codewithak.dailypulse.presentation.NewsViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {

    single<NewsApiService>{
        NewsApiServiceImpl()
    }

    factory {
        NewsViewModel(get())
    }
}


fun initKoin(){
    startKoin {
        modules(appModule)
    }
}