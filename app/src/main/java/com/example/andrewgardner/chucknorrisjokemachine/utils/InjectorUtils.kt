package com.example.andrewgardner.chucknorrisjokemachine.utils

import com.example.andrewgardner.chucknorrisjokemachine.model.SomeTestRepository
import com.example.andrewgardner.chucknorrisjokemachine.network.Api
import com.example.andrewgardner.chucknorrisjokemachine.network.PostApi
import com.example.andrewgardner.chucknorrisjokemachine.ui.post.PostListViewModelFactory
import retrofit2.Retrofit

object InjectorUtils {

    //private var retrofit : Retrofit

    fun getSomeTestRepository(): SomeTestRepository {
        return SomeTestRepository.getInstance()
    }

    fun postListViewModelFactory(): PostListViewModelFactory {
        return PostListViewModelFactory(getSomeTestRepository())
    }

    internal fun providePostApi(): Api {
        return PostApi.create()!!
    }
}
