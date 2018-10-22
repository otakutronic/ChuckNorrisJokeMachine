package com.example.andrewgardner.chucknorrisjokemachine.ui.joke

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.andrewgardner.chucknorrisjokemachine.network.ChuckNorrisRepository

/**
 * Factory for creating a [JokeViewMode] with a constructor that takes a [ChuckNorrisRepository]
 */
class JokeViewModelFactory(
        private val chuckNorrisRepository: ChuckNorrisRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return JokeViewModel(chuckNorrisRepository) as T
    }
}
