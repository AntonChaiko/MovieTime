package com.example.movietime.ui.fragments.moviesfragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietime.data.glide.ImageLoader
import com.example.movietime.databinding.MovieItemBinding
import com.example.movietime.domain.models.ReviewsEntity
import javax.inject.Inject

class MoviesAdapter @Inject constructor(

    private val imageLoader: ImageLoader

) : PagingDataAdapter<ReviewsEntity, MoviesAdapter.MoviesViewHolder>(MoviesDiffItemCallback()) {

    inner class MoviesViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movieEntity:ReviewsEntity) {
            val (imageSrc,title,review) = movieEntity
            with(binding) {
                binding.entity = movieEntity
               imageLoader.loadImage(binding.movieImageView,imageSrc)
            }
        }
    }

    override fun onBindViewHolder(holder: MoviesAdapter.MoviesViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MoviesViewHolder {
        return MoviesViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}