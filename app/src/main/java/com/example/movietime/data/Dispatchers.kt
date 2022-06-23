package com.example.movietime.data

import kotlinx.coroutines.CoroutineDispatcher


interface Dispatchers {
    fun dispatcher(): CoroutineDispatcher

    class Main() : Dispatchers {
        override fun dispatcher(): CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Main
    }

    class Io() : Dispatchers {
        override fun dispatcher(): CoroutineDispatcher = kotlinx.coroutines.Dispatchers.IO
    }

    class Default() : Dispatchers {
        override fun dispatcher(): CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Default
    }

    class Unconfined() : Dispatchers {
        override fun dispatcher(): CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Unconfined
    }
}