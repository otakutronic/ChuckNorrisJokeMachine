package com.example.andrewgardner.chucknorrisjokemachine.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.andrewgardner.chucknorrisjokemachine.model.SomeTestRepository
import com.example.andrewgardner.chucknorrisjokemachine.viewmodels.WebViewViewModel

/**
 * Factory for creating a [WebViewViewModel] with a constructor that takes a [SomeTestRepository]
 * for the current [TestObject].
 */
class WebViewViewModelFactory(
        private val testRepository: SomeTestRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WebViewViewModel(testRepository) as T
    }
}
