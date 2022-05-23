package com.lpirro.cryptomovies.presentation.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.lpirro.cryptomovies.databinding.WatchlistFragmentBinding
import com.lpirro.cryptomovies.presentation.base.BaseFragment
import com.lpirro.cryptomovies.presentation.watchlist.viewmodel.WatchlistViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WatchlistFragment : BaseFragment<WatchlistFragmentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> WatchlistFragmentBinding
        get() = WatchlistFragmentBinding::inflate

    private val viewModel: WatchlistViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.watchlist.collect { updateUi(it) }
        }
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
                val z = 0
            }
        }
    }
}
