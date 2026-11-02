package com.legalstream.app.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.legalstream.app.databinding.ItemMovieBinding
import com.legalstream.app.model.Movie
import com.legalstream.app.ui.PlayerActivity

class MovieAdapter(private val movies: List<Movie>) : 
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.title.text = movie.title
        
        Glide.with(holder.itemView.context)
            .load(movie.thumbnailUrl.ifEmpty { "https://archive.org/services/img/\" })
            .centerCrop()
            .into(holder.binding.thumbnail)
        
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PlayerActivity::class.java)
            intent.putExtra("videoUrl", movie.videoUrl)
            intent.putExtra("title", movie.title)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = movies.size
}
