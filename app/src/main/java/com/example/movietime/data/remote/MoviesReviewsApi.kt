package com.example.movietime.data.remote

import com.example.movietime.BuildConfig
import com.example.movietime.domain.models.ReviewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesReviewsApi {
    @GET("all.json")
    suspend fun getMoviesReviews(
        @Query("api-key") apiKey: String = BuildConfig.API_KEY,
        @Query("offset") offset: Int
    ): ReviewsResponse
}