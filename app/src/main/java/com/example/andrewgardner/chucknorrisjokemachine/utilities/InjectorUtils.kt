package com.example.andrewgardner.chucknorrisjokemachine.utilities

import com.example.andrewgardner.chucknorrisjokemachine.viewmodels.WebViewViewModelFactory
import com.zozo.pb.repo.SomeTestRepository

object InjectorUtils {

    fun getSomeTestRepository(): SomeTestRepository {
        return SomeTestRepository.getInstance()
    }

    fun provideWebViewViewModelFactory(): WebViewViewModelFactory {
        return WebViewViewModelFactory(getSomeTestRepository())
    }
}
