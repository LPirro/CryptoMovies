package com.lpirro.cryptomovies.presentation.details.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexboxLayoutManager
import com.lpirro.cryptomovies.databinding.MovieDetailInforViewBinding
import com.lpirro.cryptomovies.presentation.details.adapter.CastAdapter
import com.lpirro.cryptomovies.presentation.details.adapter.GenresAdapter
import com.lpirro.cryptomovies.presentation.details.adapter.SpaceItemDecoration

class DetailsInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        MovieDetailInforViewBinding.inflate(LayoutInflater.from(context), this, true)

    private val genresAdapter = GenresAdapter()
    private val castAdapter = CastAdapter()

    private val spaceDecorator: SpaceItemDecoration by lazy {
        SpaceItemDecoration()
    }

    var overview: String? = null
        set(value) {
            field = value
            value?.let { binding.movieOverview.text = it }
        }

    var originalTitle: String? = null
        set(value) {
            field = value
            value?.let { binding.originalTitleValue.text = it }
        }

    var status: String? = null
        set(value) {
            field = value
            value?.let { binding.statusValue.text = it }
        }

    var originalLanguage: String? = null
        set(value) {
            field = value
            value?.let { binding.originalLanguageValue.text = it }
        }

    var budget: String? = null
        set(value) {
            field = value
            value?.let { binding.budgetValue.text = it }
        }

    var revenue: String? = null
        set(value) {
            field = value
            value?.let { binding.revenueValue.text = it }
        }

    var cast: List<String>? = null
        set(value) {
            field = value
            value?.let { castAdapter.setData(it) }
        }

    var genres: List<String>? = null
        set(value) {
            field = value
            value?.let { genresAdapter.setData(it) }
        }

    init {
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() {
        binding.apply {
            genresRecyclerView.removeItemDecoration(spaceDecorator)
            genresRecyclerView.apply {
                layoutManager = FlexboxLayoutManager(context)
                adapter = genresAdapter
                addItemDecoration(spaceDecorator)
            }

            castRecyclerView.removeItemDecoration(spaceDecorator)
            castRecyclerView.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = castAdapter
                addItemDecoration(spaceDecorator)
            }
        }
    }
}
