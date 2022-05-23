package com.lpirro.cryptomovies.presentation.details.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.lpirro.cryptomovies.R
import com.lpirro.cryptomovies.databinding.MovieDetailHeaderViewBinding

class DetailsHeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        MovieDetailHeaderViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.watchlistButton.setOnClickListener { watchlistClickListener?.invoke() }
    }

    var backdropImage: String? = null
        set(value) {
            field = value
            value?.let { setBackdrop(value) }
        }

    var moviePoster: String? = null
        set(value) {
            field = value
            value?.let { setPoster(value) }
        }

    var movieTitle: String? = null
        set(value) {
            field = value
            value?.let { setTitle(value) }
        }

    var headerInfo: String? = null
        set(value) {
            field = value
            value?.let { setInfo(value) }
        }

    private fun setBackdrop(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(binding.backdropImage)
    }

    private fun setPoster(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
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
    }

    private fun setTitle(title: String) {
        binding.movieHeaderTitle.text = title
    }

    private fun setInfo(info: String) {
        binding.movieHeaderInfo.text = info
    }

    var watchlistClickListener: (() -> Unit)? = null
}
