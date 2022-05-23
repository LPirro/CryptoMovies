package com.lpirro.cryptomovies.presentation.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.lpirro.cryptomovies.R
import com.lpirro.cryptomovies.databinding.WatchlistFragmentBinding
import com.lpirro.cryptomovies.presentation.base.BaseFragment
import com.lpirro.cryptomovies.presentation.home.adapter.MoviePosterItemDecoration
import com.lpirro.cryptomovies.presentation.watchlist.adapter.WatchlistMoviesAdapter
import com.lpirro.cryptomovies.presentation.watchlist.viewmodel.WatchlistViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WatchlistFragment : BaseFragment<WatchlistFragmentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> WatchlistFragmentBinding
        get() = WatchlistFragmentBinding::inflate

    private val viewModel: WatchlistViewModel by viewModels()
    private lateinit var watchlistMoviesAdapter: WatchlistMoviesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        lifecycleScope.launch {
            viewModel.watchlist.collect { updateUi(it) }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }

    private fun updateUi(uiState: WatchlistViewModel.WatchlistUiState) {
        when (uiState) {
            is WatchlistViewModel.WatchlistUiState.Error -> {
                val c = 0
            }
            WatchlistViewModel.WatchlistUiState.Loading -> {
                val c = 0
            }
            is WatchlistViewModel.WatchlistUiState.Success -> {
                watchlistMoviesAdapter.movies = uiState.watchlist
            }
        }
    }

    private fun setupRecyclerView() {
        watchlistMoviesAdapter = WatchlistMoviesAdapter(::onMovieClick)
        binding.watchlistRecyclerView.apply {
            val flexboxLayoutManager = FlexboxLayoutManager(requireContext())
            flexboxLayoutManager.justifyContent = JustifyContent.CENTER
            flexboxLayoutManager.flexDirection = FlexDirection.ROW
            flexboxLayoutManager.flexWrap = FlexWrap.WRAP
            addItemDecoration(MoviePosterItemDecoration())
            layoutManager = flexboxLayoutManager
            adapter = watchlistMoviesAdapter
        }
    }

    private fun onMovieClick(movieId: Long) {
        val bundle = bundleOf("movieId" to movieId)
        findNavController().navigate(R.id.action_wishlist_to_movie_detail, bundle)
    }
}
