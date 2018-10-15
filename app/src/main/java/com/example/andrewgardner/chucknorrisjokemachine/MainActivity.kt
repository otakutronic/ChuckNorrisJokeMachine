package com.example.andrewgardner.chucknorrisjokemachine

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.andrewgardner.chucknorrisjokemachine.utilities.InjectorUtils
import com.example.andrewgardner.chucknorrisjokemachine.viewmodels.WebViewViewModel

import kotlinx.android.synthetic.main.activity_main.*
import android.widget.TextView



class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: WebViewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // globally
        val myAwesomeTextView = findViewById(R.id.textView) as TextView

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            viewModel.loadPosts()
        }

        val factory = InjectorUtils.provideWebViewViewModelFactory()
        viewModel = ViewModelProviders.of(this, factory)
                .get(WebViewViewModel::class.java)

        // observe greeting livedata
        viewModel.wikiPosts().observe(this, Observer { greeting ->
            Log.e("STUFF", greeting.toString())
            //in your OnCreate() method
            myAwesomeTextView.text = greeting.toString()

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
