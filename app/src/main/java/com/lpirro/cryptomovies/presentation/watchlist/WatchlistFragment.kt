package com.lpirro.cryptomovies.presentation.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.lpirro.cryptomovies.R
import com.lpirro.cryptomovies.databinding.WatchlistFragmentBinding
import com.lpirro.cryptomovies.presentation.base.BaseFragment
import com.lpirro.cryptomovies.presentation.watchlist.adapter.GridSpacingItemDecoration
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
        binding.startExploringButton.setOnClickListener {
            findNavController().navigate(R.id.navigation_home)
        }

        when (uiState) {
            is WatchlistViewModel.WatchlistUiState.Error -> {
            }
            WatchlistViewModel.WatchlistUiState.Loading -> {
            }
            is WatchlistViewModel.WatchlistUiState.Success -> {
                binding.emptyWatchList.visibility = View.GONE
                watchlistMoviesAdapter.movies = uiState.watchlist
            }
            WatchlistViewModel.WatchlistUiState.Empty -> {
                binding.emptyWatchList.visibility = View.VISIBLE
            }
        }
    }

    private fun setupRecyclerView() {
        val spacing = resources.getDimensionPixelSize(R.dimen.margin_16dp)
        watchlistMoviesAdapter = WatchlistMoviesAdapter(::onMovieClick)
        binding.watchlistRecyclerView.apply {
            val lm = GridLayoutManager(requireContext(), 3)
            addItemDecoration(GridSpacingItemDecoration(3, spacing, true))
            layoutManager = lm
            adapter = watchlistMoviesAdapter
        }
    }

    private fun onMovieClick(movieId: Long) {
        val bundle = bundleOf("movieId" to movieId)
        findNavController().navigate(R.id.action_wishlist_to_movie_detail, bundle)
    }
}
