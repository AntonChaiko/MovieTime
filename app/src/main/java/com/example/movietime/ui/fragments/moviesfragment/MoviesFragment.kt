package com.example.movietime.ui.fragments.moviesfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movietime.databinding.FragmentMoviesBinding
import com.example.movietime.ui.fragments.basefragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMoviesBinding = FragmentMoviesBinding.inflate(inflater, container, false)


}
