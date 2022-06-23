package com.example.movietime.ui.fragments.moviesfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movietime.BuildConfig
import com.example.movietime.domain.models.ReviewsEntity
import com.example.movietime.domain.use_case.SearchResultStreamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val paging: SearchResultStreamUseCase
) : ViewModel() {

    var currentSearchResult: Flow<PagingData<ReviewsEntity>>? = null

    fun resultStream(): Flow<PagingData<ReviewsEntity>> {
        val lastResult = currentSearchResult
        if (lastResult != null) {
            return lastResult
        }
        val newResult: Flow<PagingData<ReviewsEntity>> = paging.invoke().cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

}