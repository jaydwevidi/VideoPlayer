package com.example.videoplayer

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView

class PlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        val videoView = findViewById<VideoView>(R.id.videoView)

        videoView.setVideoURI(Uri.parse(intent.getStringExtra("myPath")))
        val listnerObject = object : MediaPlayer.OnPreparedListener {
            override fun onPrepared(p0: MediaPlayer?) {
               videoView.start()
            }

        }
        videoView.setOnPreparedListener (listnerObject)
    }
}