package com.example.movietime.ui.fragments.splashfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.movietime.R
import com.example.movietime.databinding.FragmentSplashBinding
import com.example.movietime.ui.fragments.basefragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(),CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launch {
            delay(2000)
            findNavController().navigate(R.id.action_splashFragment_to_moviesFragment)
        }

    }
    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding = FragmentSplashBinding.inflate(inflater, container, false)


}