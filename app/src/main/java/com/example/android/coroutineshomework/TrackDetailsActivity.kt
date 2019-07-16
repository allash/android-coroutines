package com.example.android.coroutineshomework

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_track_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrackDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_details)

        val name = intent.getStringExtra(EXTRA_TRACK_NAME)
        trackDetailsTextView.text = name

        val image = intent.getStringExtra(EXTRA_TRACK_IMAGE_URL)
        Picasso.get().load(image).into(trackImageView)

        CoroutineScope(Dispatchers.IO).launch {
            val response = Picasso.get().load(image)
        }
    }
}
