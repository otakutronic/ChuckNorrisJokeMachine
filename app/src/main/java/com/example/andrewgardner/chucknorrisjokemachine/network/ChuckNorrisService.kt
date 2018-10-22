package com.example.andrewgardner.chucknorrisjokemachine.network

import com.example.andrewgardner.chucknorrisjokemachine.model.Joke
import retrofit2.Call
import retrofit2.http.GET

interface ChuckNorrisService {

  @GET("/jokes/random")
  fun getRandomJoke(): Call<Joke>

}