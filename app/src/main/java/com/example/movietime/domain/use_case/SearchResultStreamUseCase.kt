package com.example.movietime.domain.use_case

import androidx.paging.PagingData
import com.example.movietime.domain.models.ReviewsEntity
import com.example.movietime.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchResultStreamUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    operator fun invoke(): Flow<PagingData<ReviewsEntity>> {
        return repository.getSearchResultStream()
    }

}