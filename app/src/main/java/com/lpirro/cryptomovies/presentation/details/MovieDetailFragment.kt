package com.lpirro.cryptomovies.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.lpirro.cryptomovies.R
import com.lpirro.cryptomovies.databinding.MovieDetailFragmentBinding
import com.lpirro.cryptomovies.domain.model.Movie
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

        viewModel.fetchMovieDetail(args.movieId)
        lifecycleScope.launch {
            viewModel.movie.collect {
                updateUi(it)
            }
        }
    }

    private fun updateUi(uiState: MovieDetailViewModel.MovieDetailUiState) {
        when (uiState) {
            is MovieDetailViewModel.MovieDetailUiState.Error -> {
            }
            MovieDetailViewModel.MovieDetailUiState.Loading -> {
            }
            is MovieDetailViewModel.MovieDetailUiState.Success -> {
                setupViews(uiState.movie)
            }
        }
    }

    private fun setupViews(movie: Movie) {
        Glide.with(this)
            .load(movie.backdropPath).into(binding.backdropImage)
        Glide.with(this)
            .load(movie.posterUrl)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(
                RequestOptions.bitmapTransform(
                    RoundedCorners(
                        resources.getDimensionPixelSize(R.dimen.movie_poster_corner_radius)
                    )
                )
            )
            .into(binding.moviePoster)

        binding.movieHeaderTitle.text = movie.title
        binding.movieHeaderInfo.text = movie.releaseDate
    }
}
