package com.example.android.coroutineshomework.views.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.android.coroutineshomework.R
import com.example.android.coroutineshomework.network.RetrofitFactory
import com.example.android.coroutineshomework.views.adapters.MoviesAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayoutManager = LinearLayoutManager(this)
        moviesRecyclerView.layoutManager = linearLayoutManager

        val apiService = RetrofitFactory.build()

        val ctx = this
        job = CoroutineScope(Dispatchers.IO).launch {

            val response = apiService.getPopularMovies()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            it.results?.let { movies ->
                                moviesAdapter =
                                    MoviesAdapter(movies) { ctx, movie ->
                                        val intent = Intent(ctx, MovieDetailsActivity::class.java)
                                        intent.putExtra(EXTRA_MOVIE, movie)
                                        ctx.startActivity(intent)
                                    }
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

    override fun onStop() {
        super.onStop()
        job.cancel()
    }
}

