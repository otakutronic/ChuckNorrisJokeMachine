package com.example.andrewgardner.chucknorrisjokemachine.utils

import com.example.andrewgardner.chucknorrisjokemachine.network.ChuckNorrisRepository
import com.example.andrewgardner.chucknorrisjokemachine.ui.joke.JokeViewModelFactory

object InjectorUtils {

    fun jokeRepository(): ChuckNorrisRepository {
        return ChuckNorrisRepository.getInstance()
    }

    fun jokeViewModelFactory(): JokeViewModelFactory {
        return JokeViewModelFactory(jokeRepository())
    }


}
