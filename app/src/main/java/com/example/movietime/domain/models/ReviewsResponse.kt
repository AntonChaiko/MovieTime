package com.example.movietime.domain.models

data class ReviewsResponse(
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)