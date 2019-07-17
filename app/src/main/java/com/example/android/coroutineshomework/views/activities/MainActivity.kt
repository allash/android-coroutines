package com.example.android.coroutineshomework.views.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.android.coroutineshomework.R
import com.example.android.coroutineshomework.network.RetrofitFactory
import com.example.android.coroutineshomework.views.adapters.MoviesAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val apiService by lazy { RetrofitFactory.build() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)
        moviesRecyclerView.layoutManager = linearLayoutManager

        val ctx = this
        CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.getPopularMovies()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            it.results?.let { movies ->
                                moviesAdapter =
                                    MoviesAdapter(movies)
                                moviesRecyclerView.adapter = moviesAdapter
                            }
                        }
                    } else {
                        Toast.makeText(ctx, "Response error: ${response.errorBody()}", Toast.LENGTH_SHORT)
                    }
                } catch (e: Throwable) {
                    Toast.makeText(ctx, "Unknown error: ${e.message}", Toast.LENGTH_SHORT)
                }
            }
        }
    }
}

