package com.example.andrewgardner.chucknorrisjokemachine.network

import com.example.andrewgardner.chucknorrisjokemachine.model.Joke
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import android.util.Log
//import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User
import retrofit2.Call
import retrofit2.Response
//import javax.swing.UIManager.put
///import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User
//import javax.swing.UIManager.put

/**
 * Repository module for handling data operations.
 */
class ChuckNorrisRepository {

    private val service: ChuckNorrisService

    // Simple in-memory cache. Details omitted for brevity.
    //private val userCache: UserCache? = null

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

    /*private val callback = object : Callback<Joke> {
        override fun onResponse(call: Call<Joke>?, response: Response<Joke>?) {
            val joke = response?.body()
            //jokeText.value = joke?.value
        }

        override fun onFailure(call: Call<Joke>?, t: Throwable?) {
            Log.v("Error", t.toString())
        }
    }*/

    fun getRandomJoke2(): MutableLiveData<Joke> {
        /*val cached = userCache!!.get()
        if (cached != null) {
            return cached
        }

        val data = MutableLiveData<Joke>()
        userCache!!.put(data)
*/

        val data = MutableLiveData<Joke>()
        //val data Callback<Joke>
        // This implementation is still suboptimal but better than before.
        // A complete implementation also handles error cases.
        service.getRandomJoke().enqueue(object : Callback<Joke> {
            override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                Log.e("STUFF", response.body().toString())
                //data.setValue(response.body())
                //val joke = response?.body()
                //data.value = joke
            }
            override fun onFailure(call: Call<Joke>?, t: Throwable?) {
                Log.v("Error", t.toString())
            }
        })

        return data
    }
}
