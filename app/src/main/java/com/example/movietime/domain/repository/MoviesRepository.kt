package com.example.movietime.domain.repository

import androidx.paging.PagingData
import com.example.movietime.domain.models.ReviewsEntity
import com.example.movietime.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
   suspend fun getMovies(offset: Int): Flow<Resource<List<ReviewsEntity>>>
   fun getSearchResultStream(): Flow<PagingData<ReviewsEntity>>
}