package com.example.android.coroutineshomework

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.track_item_cell.view.*

class TracksAdapter(
    private val context: Context,
    private val tracks: List<Track>
) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(pos: Int, converterView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.track_item_cell, parent, false)

        val track = getItem(pos)
        rowView.trackTitleTextView.text = track.name

        return rowView
    }

    override fun getItem(pos: Int): Track {
        return tracks[pos]
    }

    override fun getItemId(pos: Int): Long {
        return pos.toLong()
    }

    override fun getCount(): Int {
        return tracks.size
    }
}