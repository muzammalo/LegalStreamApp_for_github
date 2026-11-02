package com.legalstream.app.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.legalstream.app.adapter.MovieAdapter
import com.legalstream.app.databinding.ActivityMainBinding
import com.legalstream.app.model.Movie
import com.legalstream.app.network.RetrofitClient
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val moviesList = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = MovieAdapter(moviesList)
        
        loadMovies()
    }
    
    private fun loadMovies() {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.api.searchMovies()
                if (response.isSuccessful) {
                    val docs = response.body()?.response?.docs ?: emptyList()
                    for (doc in docs) {
                        val videoUrl = "https://archive.org/download/\/\.mp4"
                        moviesList.add(
                            Movie(
                                identifier = doc.identifier,
                                title = doc.title,
                                videoUrl = videoUrl,
                                thumbnailUrl = "https://archive.org/services/img/\"
                            )
                        )
                    }
                    binding.recyclerView.adapter?.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error: \", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
