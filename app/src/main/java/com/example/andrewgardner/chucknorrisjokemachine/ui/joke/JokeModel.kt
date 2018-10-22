package com.example.andrewgardner.chucknorrisjokemachine.ui.joke

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.andrewgardner.chucknorrisjokemachine.model.Joke
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokeModel(
        jokeRepository: JokeRepository
) : ViewModel() {

    companion object {
        private const val KEY_CURRENT_WEBVIEW = "currentWebView"
    }

    public var jokeText: MutableLiveData<String>  = MutableLiveData()

    private val repoRetriever = JokeRepository()

    fun getText(): MutableLiveData<String> {
        if (jokeText == null) {
            jokeText = MutableLiveData()
        }
        return jokeText as MutableLiveData<String>
    }

    private val callback = object : Callback<Joke> {
        override fun onResponse(call: Call<Joke>?, response: Response<Joke>?) {
            var json = response?.body()
            jokeText.value = json?.value.toString()
        }

        override fun onFailure(call: Call<Joke>?, t: Throwable?) {
            Log.v("Error", t.toString())
        }
    }

    fun onClickButton() {
        loadJoke()
    }

    // called from view
    fun loadJoke() {
        repoRetriever.getRandomJoke(callback)
    }
}
