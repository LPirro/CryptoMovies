package com.lpirro.cryptomovies.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lpirro.cryptomovies.databinding.MovieDetailFragmentBinding
import com.lpirro.cryptomovies.domain.model.Movie
import com.lpirro.cryptomovies.domain.model.MovieDetail
import com.lpirro.cryptomovies.presentation.base.BaseFragment
import com.lpirro.cryptomovies.presentation.details.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailFragmentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> MovieDetailFragmentBinding
        get() = MovieDetailFragmentBinding::inflate

    private val viewModel: MovieDetailViewModel by viewModels()
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.start(args.movieId)
        lifecycleScope.launch {
            viewModel.movie.collect { updateUi(it) }
        }
    }

    private fun updateUi(uiState: MovieDetailViewModel.MovieDetailUiState) {
        resetViews()
        when (uiState) {
            is MovieDetailViewModel.MovieDetailUiState.Error -> {
                binding.errorView.visibility = View.VISIBLE
            }
            MovieDetailViewModel.MovieDetailUiState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is MovieDetailViewModel.MovieDetailUiState.HeaderSuccess -> {
                setupHeader(uiState.movie)
            }
            is MovieDetailViewModel.MovieDetailUiState.DetailSuccess -> {
                setupMovieDetails(uiState.movie)
            }
            is MovieDetailViewModel.MovieDetailUiState.WatchListEvent -> {
                updateWatchlistButton(uiState.isAlreadyOnWatchList)
            }
        }
    }

    private fun setupHeader(movie: Movie) {
        binding.apply {
            movieHeaderView.backdropImage = movie.backdropPath
            movieHeaderView.moviePoster = movie.posterUrl
            movieHeaderView.movieTitle = movie.title
            movieHeaderView.headerInfo = movie.releaseDate
            backArrow.setOnClickListener { findNavController().popBackStack() }
            movieHeaderView.watchlistClickListener = { viewModel.addToWatchlist(movie.id) }
        }
    }

    private fun setupMovieDetails(movieDetail: MovieDetail) {
        binding.apply {
            movieInfoView.overview = movieDetail.overview
            movieInfoView.genres = movieDetail.genres
            movieInfoView.cast = movieDetail.castImages
            movieInfoView.originalTitle = movieDetail.originalTitle
            movieInfoView.status = movieDetail.status
            movieInfoView.originalLanguage = movieDetail.originalLanguage
            movieInfoView.budget = movieDetail.budget
            movieInfoView.revenue = movieDetail.revenue
            movieInfoView.visibility = View.VISIBLE
        }
    }

    private fun updateWatchlistButton(alreadyOnWatchList: Boolean) {
        binding.movieHeaderView.isAlreadyOnWatchlist = alreadyOnWatchList
    }

    private fun resetViews() {
        binding.apply {
            errorView.visibility = View.GONE
            progressBar.visibility = View.GONE
            movieInfoView.visibility = View.GONE
        }
    }
}
