package com.example.andrewgardner.chucknorrisjokemachine.ui.joke

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

/**
 * Factory for creating a [JokeViewMode] with a constructor that takes a [JokeRepository]
 * for the current [Joke].
 */
class JokeViewModelFactory(
        private val postListRepository: JokeRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return JokeModel(postListRepository) as T
    }
}
