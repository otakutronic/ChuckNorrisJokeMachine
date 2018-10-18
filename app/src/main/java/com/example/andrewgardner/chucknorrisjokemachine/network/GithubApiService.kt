package com.example.andrewgardner.chucknorrisjokemachine.network

import com.example.andrewgardner.chucknorrisjokemachine.model.Book
import com.example.andrewgardner.chucknorrisjokemachine.model.Model
import io.reactivex.Observable
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import com.example.andrewgardner.chucknorrisjokemachine.model.UsersList
import retrofit2.Call


interface GithubApiService {

    @GET("users?q=rokano")
    fun getUsers(): Call<UsersList>

    /*Retrofit get annotation with our URL
       And our method that will return us the list ob Book
    */
    @GET("/bins/jul6f")
    fun getBooks(response: Callback<List<Book>>)

    @GET("api.php")
    fun hitCountCheck(@Query("action") action: String,
                       @Query("format") format: String,
                       @Query("list") list: String,
                       @Query("srsearch") srsearch: String): Observable<Model.Result>

    companion object {
        fun create(): GithubApiService {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.chucknorris.io/jokes/random/")
                    .build()

            return retrofit.create(GithubApiService::class.java)
        }
    }

}