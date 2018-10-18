package com.example.andrewgardner.chucknorrisjokemachine.util

import com.example.andrewgardner.chucknorrisjokemachine.model.SomeTestRepository
import com.example.andrewgardner.chucknorrisjokemachine.network.PostApi
import com.example.andrewgardner.chucknorrisjokemachine.viewmodels.WebViewViewModelFactory
import retrofit2.Retrofit

object InjectorUtils {

    //private var retrofit : Retrofit

    fun getSomeTestRepository(): SomeTestRepository {
        return SomeTestRepository.getInstance()
    }

    fun provideWebViewViewModelFactory(): WebViewViewModelFactory {
        return WebViewViewModelFactory(getSomeTestRepository())
    }

    internal fun providePostApi(retrofit: Retrofit): PostApi {
        return retrofit.create(PostApi::class.java)
    }
}
