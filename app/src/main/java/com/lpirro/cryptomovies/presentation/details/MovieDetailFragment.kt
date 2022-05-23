package com.lpirro.cryptomovies.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexboxLayoutManager
import com.lpirro.cryptomovies.databinding.MovieDetailFragmentBinding
import com.lpirro.cryptomovies.domain.model.Movie
import com.lpirro.cryptomovies.domain.model.MovieDetail
import com.lpirro.cryptomovies.presentation.base.BaseFragment
import com.lpirro.cryptomovies.presentation.details.adapter.CastAdapter
import com.lpirro.cryptomovies.presentation.details.adapter.GenresAdapter
import com.lpirro.cryptomovies.presentation.details.adapter.SpaceItemDecoration
import com.lpirro.cryptomovies.presentation.details.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailFragmentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> MovieDetailFragmentBinding
        get() = MovieDetailFragmentBinding::inflate

    private val viewModel: MovieDetailViewModel by viewModels()
    private val args: MovieDetailFragmentArgs by navArgs()

    private val spaceDecorator: SpaceItemDecoration by lazy {
        SpaceItemDecoration()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.start(args.movieId)

        lifecycleScope.launch {
            viewModel.movie.collect {
                updateUi(it)
            }
        }
    }

    private fun updateUi(uiState: MovieDetailViewModel.MovieDetailUiState) {
        when (uiState) {
            is MovieDetailViewModel.MovieDetailUiState.Error -> {
                val c = ""
            }
            MovieDetailViewModel.MovieDetailUiState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.movieDetailLayout.visibility = View.GONE
            }
            is MovieDetailViewModel.MovieDetailUiState.HeaderSuccess -> {
                binding.progressBar.visibility = View.GONE
                setupHeader(uiState.movie)
            }
            is MovieDetailViewModel.MovieDetailUiState.DetailSuccess -> {
                binding.progressBar.visibility = View.GONE
                binding.movieDetailLayout.visibility = View.VISIBLE
                setupMovieDetails(uiState.movie)
            }
        }
    }

    private fun setupHeader(movie: Movie) {
        binding.movieHeader.backdropImage = movie.backdropPath
        binding.movieHeader.moviePoster = movie.posterUrl
        binding.movieHeader.movieTitle = movie.title
        binding.movieHeader.headerInfo = movie.releaseDate
    }

    private fun setupMovieDetails(movieDetail: MovieDetail) {
        val genresAdapter = GenresAdapter()
        val castAdapter = CastAdapter()

        binding.apply {
            movieOverview.text = movieDetail.overview
            infoLayout.originalTitleValue.text = movieDetail.originalTitle
            infoLayout.statusValue.text = movieDetail.status
            infoLayout.originalLanguageValue.text = movieDetail.originalLanguage
            infoLayout.budgetValue.text = movieDetail.budget
            infoLayout.revenueValue.text = movieDetail.revenue

            genresRecyclerView.removeItemDecoration(spaceDecorator)
            genresRecyclerView.apply {
                layoutManager = FlexboxLayoutManager(requireContext())
                adapter = genresAdapter
                addItemDecoration(spaceDecorator)
            }

            castRecyclerView.removeItemDecoration(spaceDecorator)
            castRecyclerView.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = castAdapter
                addItemDecoration(spaceDecorator)
            }
        }
        genresAdapter.setData(movieDetail.genres)
        castAdapter.setData(movieDetail.castImages)
    }
}
