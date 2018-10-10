package com.example.andrewgardner.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.zozo.pb.repo.SomeTestRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class WebViewViewModel(
        testRepository: SomeTestRepository
) : ViewModel() {

    companion object {
        private const val KEY_CURRENT_WEBVIEW = "currentWebView"
    }

    private val greeting: MutableLiveData<String> = MutableLiveData()
    private val disposables = CompositeDisposable()
    private val testRepository: SomeTestRepository = testRepository

    // observed from view
    fun greeting(): MutableLiveData<String> {
        return greeting
    }

    // called from view
    fun loadFooterGreeting2(): MutableLiveData<String> = loadGreeting2(testRepository)

    // called from view
    fun loadFooterGreeting() {
        loadGreeting(testRepository)
    }

    override fun onCleared() {
        //disposables.clear()
    }

    private fun loadGreeting2(testRepository: SomeTestRepository): MutableLiveData<String> {
        return testRepository.getMessage()
                .observeOn(AndroidSchedulers.mainThread()).toLiveData()
    }

    private fun loadGreeting(testRepository: SomeTestRepository) {
        disposables.add(testRepository.getMessage()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { myData -> greeting.value = myData },
                        { throwable -> greeting.value = "Error message" }
                )
        )
    }
}

fun <T> Single<T>.toLiveData(): MutableLiveData<T> {

    return object : MutableLiveData<T>() {
        var disposable: Disposable? = null

        // ライフサイクルがActiveになったときに購読開始
        override fun onActive() {
            super.onActive()

            // Observable -> LiveData
            disposable = this@toLiveData.subscribe({
                this.postValue(it)
            }, { error ->
            })
        }

        // ライフサイクルが非Activeになったら購読停止
        override fun onInactive() {
            disposable?.dispose()
            super.onInactive()
        }
    }
}
