package com.example.android.coroutineshomework.views.adapters

import android.content.Intent
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.coroutineshomework.R
import com.example.android.coroutineshomework.config.Constants
import com.example.android.coroutineshomework.dto.DtoMovie
import com.example.android.coroutineshomework.views.activities.MovieDetailsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item_cell.view.*

class MoviesAdapter(
    private val movies: List<DtoMovie>
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflatedView = parent.inflate(R.layout.movie_item_cell, false)
        return MovieViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int): Unit = holder.bind(movies[position])

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private var movieView: View = view
        private var movie: DtoMovie? = null

        init {
            view.setOnClickListener(this)
        }

        fun bind(movie: DtoMovie) {
            this.movie = movie
            movieView.movieTitleTextView.text = movie.originalTitle
            Picasso.get().load("${Constants.BASE_IMAGE_PATH}${movie.posterPath}")
                .into(movieView.moviePosterView)
        }

        override fun onClick(view: View?) {
            val ctx = movieView.context
            val intent = Intent(ctx, MovieDetailsActivity::class.java)
            intent.putExtra(Constants.EXTRA_MOVIE, movie)
            ctx.startActivity(intent)
        }
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }
}