package com.lpirro.cryptomovies.presentation.home.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.lpirro.cryptomovies.R
import com.lpirro.cryptomovies.databinding.MovieSectionViewBinding
import com.lpirro.cryptomovies.domain.model.Movie
import com.lpirro.cryptomovies.presentation.home.adapter.MovieAdapter
import com.lpirro.cryptomovies.presentation.home.adapter.MoviePosterItemDecoration

class MovieSectionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = MovieSectionViewBinding.inflate(LayoutInflater.from(context), this, true)
    private lateinit var movieAdapter: MovieAdapter

    var viewData: List<Movie>? = null
        set(value) {
            field = value
            viewData?.let { onViewDataSet(it) }
        }

    private var sectionTitle: String? = null
        set(value) {
            field = value
            value?.let { updateSectionTitle(value) }
        }

    lateinit var movieClickListener: MovieClickListener

    interface MovieClickListener {
        fun onMovieClick(movieId: Long)
    }

    private fun updateSectionTitle(title: String) {
        binding.headerTitle.text = title
    }

    private var coverType: MovieAdapter.CoverType = MovieAdapter.CoverType.NORMAL

    init {
        context.withStyledAttributes(attrs, R.styleable.MovieSectionView) {
            coverType = if (getInt(R.styleable.MovieSectionView_coverType, 0) == 0)
                MovieAdapter.CoverType.NORMAL
            else
                MovieAdapter.CoverType.BIG
            sectionTitle = getString(R.styleable.MovieSectionView_sectionTitle)
        }
        setupRecyclerView()
    }

    private fun onViewDataSet(viewData: List<Movie>) {
        movieAdapter.setData(viewData as ArrayList<Movie>)
    }

    private fun setupRecyclerView() {
        ViewCompat.setNestedScrollingEnabled(binding.movieCoverRecyclerView, false)
        movieAdapter = MovieAdapter(coverType, ::handlePosterClick)
        binding.movieCoverRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = movieAdapter
            addItemDecoration(MoviePosterItemDecoration())
        }
    }

    private fun handlePosterClick(movieId: Long) {
        movieClickListener.onMovieClick(movieId)
    }
}
