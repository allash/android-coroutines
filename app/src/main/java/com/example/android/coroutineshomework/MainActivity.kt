package com.example.android.coroutineshomework

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {

    companion object {
        val EXTRA_TRACK_ID = "extra_track_id"
        val EXTRA_TRACK_NAME = "extra_track_name"
    }

    private val TAG: String = "MainActivity"
    private lateinit var tracksAdapter: TracksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ctx = this
        val service = RetrofitFactory.build()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getTracks()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        Log.i(TAG, "Success")

                        response.body()?.let {
                            tracksAdapter = TracksAdapter(ctx, it)
                            tracksListView.adapter = tracksAdapter
                        }


                    } else {
                        Log.i(TAG, "Error")
                    }
                } catch (e: HttpException) {
                    Log.i(TAG, "Finished with exception: ${e.message()}")
                } catch (e: Throwable) {
                    Log.i(TAG, "Unknown exception: ${e.message}")
                }
            }
        }

        tracksListView.setOnItemClickListener { _, _, position, _ ->
            val track = tracksAdapter.getItem(position)
            val detailIntent = Intent(this, TrackDetailsActivity::class.java)
            detailIntent.putExtra(EXTRA_TRACK_ID, track._id)
            detailIntent.putExtra(EXTRA_TRACK_NAME, track.name)

            startActivity(detailIntent)
        }
    }
}

