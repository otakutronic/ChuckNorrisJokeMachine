package com.example.andrewgardner.chucknorrisjokemachine.utils

import com.example.andrewgardner.chucknorrisjokemachine.ui.joke.JokeRepository
import com.example.andrewgardner.chucknorrisjokemachine.ui.joke.JokeViewModelFactory

object InjectorUtils {

    //private var retrofit : Retrofit

    fun getPostListRepository(): JokeRepository {
        return JokeRepository.getInstance()
    }

    fun postListViewModelFactory(): JokeViewModelFactory {
        return JokeViewModelFactory(getPostListRepository())
    }


}
