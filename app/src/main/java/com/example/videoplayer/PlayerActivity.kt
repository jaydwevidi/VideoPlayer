package com.example.videoplayer

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import android.widget.VideoView
import com.example.videoplayer.databinding.ActivityMainBinding
import com.example.videoplayer.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.videoView.setVideoURI(Uri.parse(intent.getStringExtra("myPath")))

        val listnerObject = object : MediaPlayer.OnPreparedListener {
            override fun onPrepared(p0: MediaPlayer?) {
                //Toast.makeText(this@PlayerActivity, "starting", Toast.LENGTH_SHORT).show()

            }

        }
        binding.videoView.setOnPreparedListener (listnerObject)

        binding.pauseButton.setOnClickListener {
                binding.videoView.pause()
        }

        binding.playpause.setOnClickListener{
            binding.videoView.start()
        }


    }

}