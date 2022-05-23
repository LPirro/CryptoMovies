package com.lpirro.cryptomovies.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.lpirro.cryptomovies.R
import com.lpirro.cryptomovies.databinding.ItemHomeMoviePosterBinding
import com.lpirro.cryptomovies.domain.model.Movie

class MovieAdapter(
    private val type: CoverType = CoverType.NORMAL,
    private val moviePosterClickListener: (movieId: Long) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    enum class CoverType {
        NORMAL,
        BIG
    }

    private val movies = arrayListOf<Movie>()

    fun setData(newMovies: List<Movie>) {
        val diffCallback = MoviesDiffCallback(movies, newMovies)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        movies.clear()
        movies.addAll(newMovies)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class MovieViewHolder(val binding: ItemHomeMoviePosterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemHomeMoviePosterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        val context = holder.itemView.context
        when (type) {
            CoverType.NORMAL -> {
                val height =
                    context.resources.getDimensionPixelSize(R.dimen.movie_poster_normal_height)
                val width =
                    context.resources.getDimensionPixelSize(R.dimen.movie_poster_normal_width)
                setupCoverSize(holder.binding.movieCover, height, width)
            }
            CoverType.BIG -> {
                val height =
                    context.resources.getDimensionPixelSize(R.dimen.movie_poster_big_height)
                val width = context.resources.getDimensionPixelSize(R.dimen.movie_poster_big_width)
                setupCoverSize(holder.binding.movieCover, height, width)
            }
        }

        Glide.with(holder.itemView.context)
            .load(movie.posterUrl)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.ic_cover_placeholder)
            .apply(
                RequestOptions.bitmapTransform(
                    RoundedCorners(
                        context.resources.getDimensionPixelSize(
                            R.dimen.movie_poster_corner_radius
                        )
                    )
                )
            )
            .into(holder.binding.movieCover)

        holder.binding.movieCover.setOnClickListener {
            moviePosterClickListener.invoke(movie.id)
        }
    }

    private fun setupCoverSize(coverImageView: ImageView, height: Int, width: Int) {
        coverImageView.layoutParams.height = height
        coverImageView.layoutParams.width = width
        coverImageView.requestLayout()
    }

    override fun getItemCount() = movies.size
}
