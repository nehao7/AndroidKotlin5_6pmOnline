package com.o7solutions.androidkotlin5_6pmonline.video_view

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.o7solutions.androidkotlin5_6pmonline.R
import com.o7solutions.androidkotlin5_6pmonline.databinding.ActivityVideoViewBinding

class VideoViewActivity : AppCompatActivity() {
    private val binding: ActivityVideoViewBinding by lazy {
        ActivityVideoViewBinding.inflate(layoutInflater)
    }
    lateinit var mediaControls: MediaController
    val videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


            mediaControls = MediaController(this)
            mediaControls.setAnchorView(this.binding.videoView)
        binding.videoView.setMediaController(mediaControls)
        binding.videoView.setVideoURI(Uri.parse("android.resource://"
                + packageName + "/" + R.raw.test_video))
        binding.videoView.requestFocus()
        binding.videoView.start()

        binding.videoView.setOnCompletionListener {
            Toast.makeText(applicationContext, "Video completed",
                Toast.LENGTH_LONG).show()
            true
        }

        binding.videoView.setOnErrorListener { mp, what, extra ->
            Toast.makeText(applicationContext, "An Error Occurred " +
                    "While Playing Video !!!", Toast.LENGTH_LONG).show()
            false
        }
    }
    }



