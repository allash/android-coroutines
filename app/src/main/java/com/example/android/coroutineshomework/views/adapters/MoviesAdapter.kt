package com.example.android.coroutineshomework.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.android.coroutineshomework.R
import com.example.android.coroutineshomework.config.inflate
import com.example.android.coroutineshomework.dto.DtoMovie
import com.example.android.coroutineshomework.views.activities.MovieDetailsActivity.Companion.BASE_IMAGE_PATH
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item_cell.view.*

class MoviesAdapter(
    private val movies: List<DtoMovie>,
    private val onClick: (Context, DtoMovie) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflatedView = parent.inflate(R.layout.movie_item_cell, false)
        return MovieViewHolder(inflatedView, onClick)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) { holder.bind(movies[position]) }

    class MovieViewHolder(view: View, val onClick: (Context, DtoMovie) -> Unit) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private var movieView: View = view
        private var movie: DtoMovie? = null

        init {
            view.setOnClickListener(this)
        }

        fun bind(movie: DtoMovie) {
            this.movie = movie
            movieView.movieTitleTextView.text = movie.originalTitle
            Picasso.get().load("$BASE_IMAGE_PATH${movie.posterPath}")
                .into(movieView.moviePosterView)
        }

        override fun onClick(view: View?) {
            movie?.let { onClick(movieView.context, it)  }
        }
    }
}