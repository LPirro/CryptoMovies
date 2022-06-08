package com.lpirro.cryptomovies.presentation.watchlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.lpirro.cryptomovies.R
import com.lpirro.cryptomovies.databinding.ItemWatchlistBinding
import com.lpirro.cryptomovies.domain.model.WatchlistMovie
import com.lpirro.cryptomovies.presentation.home.adapter.MoviesDiffCallback

class WatchlistMoviesAdapter(
    private val moviePosterClickListener: (movieId: Long) -> Unit
) : RecyclerView.Adapter<WatchlistMoviesAdapter.MovieViewHolder>() {

    private val watchListMovies = arrayListOf<WatchlistMovie>()

    fun setData(newMovies: List<WatchlistMovie>) {
        val diffCallback = MoviesDiffCallback(watchListMovies, newMovies)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        watchListMovies.clear()
        watchListMovies.addAll(newMovies)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class MovieViewHolder(val binding: ItemWatchlistBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemWatchlistBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = watchListMovies[position]
        val context = holder.itemView.context

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

        holder.binding.movieCover.setOnClickListener { moviePosterClickListener.invoke(movie.id) }
    }

    override fun getItemCount(): Int {
        return watchListMovies.size
    }
}
