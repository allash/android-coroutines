package com.example.android.coroutineshomework

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
    }

    private lateinit var tracksAdapter: TracksAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)
        tracksRecyclerView.layoutManager = linearLayoutManager

        val service = RetrofitFactory.build()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getTracks()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        Log.i(TAG, "Success")

                        response.body()?.let {
                            tracksAdapter = TracksAdapter(it)
                            tracksRecyclerView.adapter = tracksAdapter
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
    }
}

