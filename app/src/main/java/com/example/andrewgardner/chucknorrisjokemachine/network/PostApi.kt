package com.example.andrewgardner.chucknorrisjokemachine.network

import com.example.andrewgardner.chucknorrisjokemachine.model.Post
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface PostApi {

    //val BASE_URL = "https://api.github.com/search/" //https://api.github.com/search/users?q=rohitkan
    /**
     * Get the list of the pots from the API
     */
    @GET("/posts")
    fun getPosts(): Observable<List<Post>>

    //const BASE_URL = "https://api.github.com/search/" //https://api.github.com/search/users?q=rohitkan

    companion object {

        fun create() : Api? {

            var retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/search/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(Api::class.java)

           /* val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.chucknorris.io/jokes/random/")
                    .build()

            return retrofit.create(PostApi::class.java)*/
        }

        /*fun create(): PostApi {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.chucknorris.io/jokes/random/")
                    .build()

            return retrofit.create(WikiApiService::class.java)
        }*/
    }
}