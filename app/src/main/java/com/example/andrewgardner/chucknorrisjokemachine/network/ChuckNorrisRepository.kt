package com.example.andrewgardner.chucknorrisjokemachine.network

import com.example.andrewgardner.chucknorrisjokemachine.model.Joke
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Repository module for handling data operations.
 */
class ChuckNorrisRepository {

    private val service: ChuckNorrisService

    companion object {
        const val BASE_URL = "https://api.chucknorris.io"

        // For Singleton instantiation
        @Volatile private var instance: ChuckNorrisRepository? = null

        fun getInstance() =
                instance
                        ?: synchronized(this) {
                    instance
                            ?: ChuckNorrisRepository().also { instance = it }
                }
    }

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(ChuckNorrisService::class.java)
    }

    fun getRandomJoke(callback: Callback<Joke>) {
        service.getRandomJoke().enqueue(callback)
    }
}
