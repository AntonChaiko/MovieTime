package com.example.movietime.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movietime.data.mapper.asReviewsEntity
import com.example.movietime.data.remote.MoviesReviewsApi
import com.example.movietime.data.remote.MoviesReviewsPagingSource
import com.example.movietime.domain.models.ReviewsEntity
import com.example.movietime.domain.repository.MoviesRepository
import com.example.movietime.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesReviewsApi: MoviesReviewsApi,
    private val dispatcher: com.example.movietime.data.Dispatchers,
) : MoviesRepository {

    override suspend fun getMovies(offset: Int): Flow<Resource<List<ReviewsEntity>>> = flow {
        emit(Resource.Loading())
        val result = moviesReviewsApi.getMoviesReviews(offset =  0)
            .asReviewsEntity()
        emit(Resource.Success(result))

    }.catch { failure -> emit(Resource.Error(failure.localizedMessage ?: "An unexpected error")) }
        .flowOn(dispatcher.dispatcher())

    override fun getSearchResultStream(): Flow<PagingData<ReviewsEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviesReviewsPagingSource(moviesReviewsApi )
            }
        ).flow
    }

}