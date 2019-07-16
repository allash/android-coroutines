package com.example.android.coroutineshomework

import android.content.Intent
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.track_item_cell.view.*

class TracksAdapter(
    private val tracks: List<Track>
) : RecyclerView.Adapter<TracksAdapter.TrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val inflatedView = parent.inflate(R.layout.track_item_cell, false)
        return TrackViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = tracks.size

    override fun onBindViewHolder(holder: TrackViewHolder, pos: Int) = holder.bind(tracks[pos])

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    class TrackViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

        private var trackView: View = view
        private var track: Track? = null

        init {
            view.setOnClickListener(this)
        }

        fun bind(item: Track) {
            track = item
            trackView.trackTitleTextView.text = item.name
        }

        override fun onClick(v: View?) {
            val ctx = trackView.context
            val detailIntent = Intent(ctx, TrackDetailsActivity::class.java)
            detailIntent.putExtra(EXTRA_TRACK_ID, track?._id)
            detailIntent.putExtra(EXTRA_TRACK_NAME, track?.name)
            detailIntent.putExtra(EXTRA_TRACK_IMAGE_URL, track?.img)

            ctx.startActivity(detailIntent)
        }
    }
}