package com.example.andrewgardner.chucknorrisjokemachine.ui.joke

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.andrewgardner.chucknorrisjokemachine.utils.InjectorUtils
import android.databinding.DataBindingUtil
import com.example.andrewgardner.chucknorrisjokemachine.R
import com.example.andrewgardner.chucknorrisjokemachine.databinding.MainActivityBinding

class JokeActivity : AppCompatActivity() {

    private lateinit var viewModel: JokeModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val factory = InjectorUtils.postListViewModelFactory()
        viewModel = ViewModelProviders.of(this, factory)
                .get(JokeModel::class.java)

        // observe joke livedata
        viewModel.jokeText.observe(this, Observer { joke ->
            Log.e("STUFF", joke.toString())
        })

        val binding : MainActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        viewModel.loadJoke()
    }
}
