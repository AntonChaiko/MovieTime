package com.example.movietime.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movietime.data.mapper.asReviewsEntity
import com.example.movietime.domain.models.ReviewsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviesReviewsPagingSource @Inject constructor(
    private val moviesReviewsApi: MoviesReviewsApi
) : PagingSource<Int, ReviewsEntity>() {

    override fun getRefreshKey(state: PagingState<Int, ReviewsEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(20)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(20)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewsEntity> =
        withContext(Dispatchers.IO) {

            val position: Int = params.key ?: 1

            return@withContext try {
                val responseData =
                    moviesReviewsApi.getMoviesReviews("GpaAWioxfKujyRzgLjcKmVdaVHAMNlfm", position)
                        .asReviewsEntity()

                LoadResult.Page(
                    data = responseData,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = position + 20
                )


            } catch (exception: IOException) {
                return@withContext LoadResult.Error(exception)
            } catch (exception: HttpException) {
                return@withContext LoadResult.Error(exception)
            }

        }

}