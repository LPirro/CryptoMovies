package com.lpirro.cryptomovies.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.lpirro.cryptomovies.R
import com.lpirro.cryptomovies.databinding.HomeFragmentBinding
import com.lpirro.cryptomovies.presentation.home.view.MovieSectionView
import com.lpirro.cryptomovies.presentation.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), MovieSectionView.MovieClickListener {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerListeners()
        lifecycleScope.launch {
            viewModel.homeScreen.collect { updateUi(it) }
        }
    }

    private fun registerListeners() {
        binding.apply {
            popularMoviesSectionView.movieClickListener = this@HomeFragment
            topRatedMoviesSectionView.movieClickListener = this@HomeFragment
            nowPlayingMoviesSectionView.movieClickListener = this@HomeFragment
            upcomingMoviesSectionView.movieClickListener = this@HomeFragment
        }
    }

    private fun updateUi(uiState: HomeViewModel.HomeUiState) {
        when (uiState) {
            is HomeViewModel.HomeUiState.Error -> {
                binding.progressBar.visibility = View.GONE
            }
            HomeViewModel.HomeUiState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is HomeViewModel.HomeUiState.Success -> {
                binding.progressBar.visibility = View.GONE
                binding.apply {
                    popularMoviesSectionView.viewData = uiState.homeScreen.popularMovies
                    topRatedMoviesSectionView.viewData = uiState.homeScreen.topRatedMovies
                    nowPlayingMoviesSectionView.viewData = uiState.homeScreen.nowPlayingMovies
                    upcomingMoviesSectionView.viewData = uiState.homeScreen.upcomingMovies
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMovieClick(movieId: Long) {
        val bundle = bundleOf("movieId" to movieId)
        findNavController().navigate(R.id.navigation_movie_detail, bundle)
    }
}
