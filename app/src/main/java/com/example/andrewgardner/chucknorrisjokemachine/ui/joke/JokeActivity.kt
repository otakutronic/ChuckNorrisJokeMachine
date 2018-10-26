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

    private lateinit var jokeViewModel: JokeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val factory = InjectorUtils.jokeViewModelFactory()
        jokeViewModel = ViewModelProviders.of(this, factory)
                .get(JokeViewModel::class.java)

        // observe joke livedata
        jokeViewModel.getText().observe(this, Observer { joke ->
            Log.d("STUFF", joke.toString())
        })

        val binding : MainActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        binding.setLifecycleOwner(this)
        binding.viewModel = jokeViewModel

        jokeViewModel.loadJoke()
    }
}
