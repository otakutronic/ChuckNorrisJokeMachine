package com.example.andrewgardner.chucknorrisjokemachine.ui.joke

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.andrewgardner.chucknorrisjokemachine.model.Joke
import com.example.andrewgardner.chucknorrisjokemachine.network.ChuckNorrisRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokeViewModel(
        private val chuckNorrisRepository: ChuckNorrisRepository
) : ViewModel() {

    private var jokeText: MutableLiveData<String>  = MutableLiveData()

    fun getText(): MutableLiveData<String> {
        if (jokeText == null) {
            jokeText = MutableLiveData()
        }
        return jokeText
    }

    private val callback = object : Callback<Joke> {
        override fun onResponse(call: Call<Joke>?, response: Response<Joke>?) {
            val joke = response?.body()
            jokeText.value = joke?.value
        }

        override fun onFailure(call: Call<Joke>?, t: Throwable?) {
            Log.v("Error", t.toString())
        }
    }

    fun onClickButton() {
        loadJoke()
    }

    fun loadJoke() {
        chuckNorrisRepository.getRandomJoke(callback)
    }
}
