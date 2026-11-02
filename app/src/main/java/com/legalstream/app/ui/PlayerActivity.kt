package com.legalstream.app.ui

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.legalstream.app.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    private var player: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        supportActionBar?.hide()
        
        val videoUrl = intent.getStringExtra("videoUrl") ?: ""
        val title = intent.getStringExtra("title") ?: ""
        supportActionBar?.title = title
        
        initializePlayer(videoUrl)
    }
    
    private fun initializePlayer(videoUrl: String) {
        player = ExoPlayer.Builder(this)
            .setMediaSourceFactory(DefaultMediaSourceFactory(this))
            .build()
        binding.playerView.player = player
        val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
        player?.setMediaItem(mediaItem)
        player?.prepare()
        player?.playWhenReady = true
    }
    
    override fun onPause() {
        super.onPause()
        player?.playWhenReady = false
    }
    
    override fun onStop() {
        super.onStop()
        player?.playWhenReady = false
    }
    
    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        player = null
    }
}
