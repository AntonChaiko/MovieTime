package com.example.movietime.domain.use_case

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@ExperimentalCoroutinesApi
abstract class FlowUseCase<T> {

    private val _trigger = MutableStateFlow(true)

    val resultFlow: Flow<T> = _trigger.flatMapLatest {offset ->
        performAction()
    }

    suspend fun launch() {
        _trigger.emit(!(_trigger.value))
    }

    protected abstract fun performAction(): Flow<T>
}