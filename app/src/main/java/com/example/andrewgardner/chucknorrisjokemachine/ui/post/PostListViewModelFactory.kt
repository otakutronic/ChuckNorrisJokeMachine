package com.example.andrewgardner.chucknorrisjokemachine.ui.post

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.andrewgardner.chucknorrisjokemachine.model.SomeTestRepository

/**
 * Factory for creating a [PostListViewMode] with a constructor that takes a [SomeTestRepository]
 * for the current [TestObject].
 */
class PostListViewModelFactory(
        private val testRepository: SomeTestRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostListViewModel(testRepository) as T
    }
}
