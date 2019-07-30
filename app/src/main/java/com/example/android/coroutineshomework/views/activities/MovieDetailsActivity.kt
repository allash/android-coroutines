package com.example.android.coroutineshomework.views.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.coroutineshomework.R
import com.example.android.coroutineshomework.dto.DtoMovie
import com.example.android.coroutineshomework.views.activities.MainActivity.Companion.EXTRA_MOVIE
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    companion object {
        const val BASE_IMAGE_PATH = "https://image.tmdb.org/t/p/w500"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movie = intent.getParcelableExtra<DtoMovie>(EXTRA_MOVIE)
        movieTitleTextView.text = movie.title
        movieOverviewTextView.text = movie.overview
        movieVoteAverageTextView.text = "Average vote rating: ${movie.voteAverage}"

        val fullPath = "$BASE_IMAGE_PATH${movie.posterPath}"

        Picasso.get().load(fullPath).into(movieImageView)
    }
}
