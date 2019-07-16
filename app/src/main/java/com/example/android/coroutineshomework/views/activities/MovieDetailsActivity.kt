package com.example.android.coroutineshomework.views.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.coroutineshomework.R
import com.example.android.coroutineshomework.config.Constants
import com.example.android.coroutineshomework.dto.DtoMovie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movie = intent.getParcelableExtra<DtoMovie>(Constants.EXTRA_MOVIE)
        movieTitleTextView.text = movie.title
        movieOverviewTextView.text = movie.overview
        movieVoteAverageTextView.text = "Average vote rating: ${movie.voteAverage}"

        val fullPath = "${Constants.BASE_IMAGE_PATH}${movie.posterPath}"

        Picasso.get().load(fullPath).into(movieImageView)
    }
}
