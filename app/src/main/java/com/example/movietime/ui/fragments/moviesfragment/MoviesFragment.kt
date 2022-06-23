package com.example.movietime.ui.fragments.moviesfragment

import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.example.movietime.data.glide.ImageLoader
import com.example.movietime.databinding.FragmentMoviesBinding
import com.example.movietime.ui.fragments.basefragment.BaseFragment
import com.example.movietime.ui.fragments.moviesfragment.adapters.MoviesAdapter
import com.example.movietime.ui.fragments.moviesfragment.adapters.MoviesLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>() {

    private val viewModel: MoviesViewModel by viewModels()

    private var searchJob: Job? = null

    private val adapter by lazy {
        MoviesAdapter (imageLoader = ImageLoader.Loader(requireContext()))
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val connectivityManager =
            getSystemService(requireContext(), ConnectivityManager::class.java)

        binding.lifecycleOwner = viewLifecycleOwner


        binding.moviesRecyclerView.adapter = adapter.withLoadStateHeaderAndFooter(

            //need to fix
            footer = MoviesLoadStateAdapter(),
            header = MoviesLoadStateAdapter()
        )

        adapter.addLoadStateListener { state: CombinedLoadStates ->
            binding.moviesRecyclerView.isVisible = state.refresh !== LoadState.Loading
            binding.loadingProgressBar.isVisible = state.refresh == LoadState.Loading
        }

        searchMovies()


        connectivityManager?.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                adapter.refresh()
            }
        })
    }

    private fun searchMovies() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.resultStream().collectLatest(adapter::submitData)
        }
    }


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMoviesBinding = FragmentMoviesBinding.inflate(inflater, container, false)


}
