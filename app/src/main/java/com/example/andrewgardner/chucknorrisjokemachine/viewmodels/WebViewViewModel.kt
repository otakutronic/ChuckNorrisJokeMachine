package com.example.andrewgardner.chucknorrisjokemachine.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.andrewgardner.chucknorrisjokemachine.model.Post
import com.example.andrewgardner.chucknorrisjokemachine.model.SomeTestRepository
import com.example.andrewgardner.chucknorrisjokemachine.model.UsersList
import com.example.andrewgardner.chucknorrisjokemachine.network.Api
import com.example.andrewgardner.chucknorrisjokemachine.network.WikiApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.arch.lifecycle.LiveData



class WebViewViewModel(
        testRepository: SomeTestRepository
) : ViewModel() {

    companion object {
        private const val KEY_CURRENT_WEBVIEW = "currentWebView"
    }

    val BASE_URL = "https://api.github.com/search/" //https://api.github.com/search/users?q=rohitkan

    private val greeting: MutableLiveData<String> = MutableLiveData()

    private val postList: MutableLiveData<List<Post>> = MutableLiveData()

    val numbers: MutableList<Int> = mutableListOf(1, 2, 3)

    private val disposables = CompositeDisposable()

    private var disposable: Disposable? = null

    private val testRepository: SomeTestRepository = testRepository

    var str : String = ""

    private var text: MutableLiveData<String>  = MutableLiveData()

    fun getText(): MutableLiveData<String> {
        if (text == null) {
            text = MutableLiveData()
        }
        return text as MutableLiveData<String>
    }

    // observed from view
    fun wikiPosts(): MutableLiveData<String> {
        return greeting
    }

    fun getUsers() {

        str = ""

        var retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        var api = retrofit.create(Api::class.java)

        var call = api.users

        call.enqueue(object : Callback<UsersList> {

            override fun onResponse(call: Call<UsersList>?, response: Response<UsersList>?) {
                var usres = response?.body()
                var user = usres?.users
                var length = user!!.size

                for (i in 0 until length) {
                    str += "\n" + user.get(i).id + " " + user.get(i).login
                }

                text.value = str
            }

            override fun onFailure(call: Call<UsersList>?, t: Throwable?) {
                Log.v("Error", t.toString())
            }
        })
    }

    // called from view
    fun loadGreeting2(): MutableLiveData<String> = loadGreeting2(testRepository)

    // called from view
    fun loadGreeting() {
        loadGreeting(testRepository)
    }

    private val wikiApiServe by lazy {
        WikiApiService.create()
    }

    // called from view
    fun loadPosts() {
        //loadWike()
        getUsers()
    }

    override fun onCleared() {
        //disposables.clear()
    }

    /*private fun loadPosts(){
        subscription = Observable.fromCallable { postDao.all }
                .concatMap {
                    dbPostList ->
                    if(dbPostList.isEmpty())
                        postApi.getPosts().concatMap {
                            apiPostList -> postDao.insertAll(*apiPostList.toTypedArray())
                            Observable.just(apiPostList)
                        }
                    else
                        Observable.just(dbPostList)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(
                        { result -> onRetrievePostListSuccess(result) },
                        { onRetrievePostListError() }
                )
    }*/

    private fun loadPostss(){
        /*disposables.add(postApi.getPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { myData -> postList.value = myData },
                        { throwable -> greeting.value = "Error message" }
                )
        )*/
    }

    private fun loadWike() {
        disposables.add(wikiApiServe.hitCountCheck("query", "json", "search", "java")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> greeting.value = "${result.query.searchinfo.totalhits} result found" },
                        { error -> greeting.value = error.message}
                )
        )
    }

    private fun loadBook() {
        //Defining the method
        //wikiApiServe.getBooks(object : Callback<List<Book>> {

        //override fun success(list: List<Book>, response: Response) {
        //Dismissing the loading progressbar
        //loading.dismiss()

        //Storing the data in our list
        //books = list

        //Calling a method to show the list
        //showList()
        // }

        //override fun failure(error: RetrofitError) {
        //you can handle the errors here
        //}
        //})
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
