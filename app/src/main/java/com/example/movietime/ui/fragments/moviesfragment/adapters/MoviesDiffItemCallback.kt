package com.example.movietime.ui.fragments.moviesfragment.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.movietime.domain.models.ReviewsEntity

class MoviesDiffItemCallback : DiffUtil.ItemCallback<ReviewsEntity>() {

    override fun areItemsTheSame(oldItem: ReviewsEntity, newItem: ReviewsEntity): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: ReviewsEntity, newItem: ReviewsEntity): Boolean = oldItem.title == newItem.title
}
