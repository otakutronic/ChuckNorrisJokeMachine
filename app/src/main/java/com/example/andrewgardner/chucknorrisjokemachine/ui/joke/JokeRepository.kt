/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.andrewgardner.chucknorrisjokemachine.ui.joke

import com.example.andrewgardner.chucknorrisjokemachine.model.Joke
import com.example.andrewgardner.chucknorrisjokemachine.network.ChuckNorrisService
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Repository module for handling data operations.
 */
class JokeRepository {

    private val service: ChuckNorrisService

    companion object {
        const val BASE_URL = "https://api.chucknorris.io"

        // For Singleton instantiation
        @Volatile private var instance: JokeRepository? = null

        fun getInstance() =
                instance ?: synchronized(this) {
                    instance ?: JokeRepository().also { instance = it }
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
