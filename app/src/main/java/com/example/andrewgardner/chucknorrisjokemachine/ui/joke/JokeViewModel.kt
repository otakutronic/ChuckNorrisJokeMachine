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

    //private var jokeText: MutableLiveData<String> = MutableLiveData()

    private var joke = MutableLiveData<Joke>()

    //private val joke: MutableLiveData<Joke>? = Transformations.switchMap(organizationIdLiveData) { organizationId -> chuckNorrisRepository.getRandomJoke2() }

    /*fun getText(): MutableLiveData<String> {
        if (jokeText == null) {
            jokeText = MutableLiveData()
        }
        return jokeText
    }*/

    fun getJoke() : MutableLiveData<Joke>? {
        if (joke == null) {
            joke = MutableLiveData()
        }
        return joke
    }

    fun setJoke(jokeData: MutableLiveData<Joke>) {
        this.joke.value = jokeData.value
    }

    /*fun fetchLists(organizationId: String, forceRefresh: Boolean = false) {
        if (organizationIdLiveData.value == null || forceRefresh) {
            organizationIdLiveData.value = organizationId
        }
    }*/

    private val callback = object : Callback<Joke> {
        override fun onResponse(call: Call<Joke>?, response: Response<Joke>?) {
            val responseValue = response?.body()
            joke.value = responseValue
        }

        override fun onFailure(call: Call<Joke>?, t: Throwable?) {
            Log.v("Error", t.toString())
        }
    }

    public fun callback2() {
        Log.e("STUFF", "Callback")
    }

    fun foo(m: String, bar: (m: String) -> Unit) {
        bar(m)
    }

    // my function to pass into the other
    fun buz(m: String) {
        println("another message: $m")
    }

    // someone passing buz into foo
    fun something() {
        foo("hi", ::buz)
    }


    fun onClickButton() {
        loadJoke()
    }

    fun loadJoke() {
        chuckNorrisRepository.getRandomJoke2(::setJoke)

        //jokeText.value = joke.value as String?
        //Log.e("STUFF", joke.value.toString())

        //chuckNorrisRepository.getRandomJoke(callback)
    }
}
