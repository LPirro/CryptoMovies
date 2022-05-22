package com.lpirro.cryptomovies.presentation.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lpirro.cryptomovies.databinding.ItemGenresBinding

class GenresAdapter : RecyclerView.Adapter<GenresAdapter.GenresViewHolder>() {

    private val genres = arrayListOf<String>()

    fun setData(newGenres: List<String>) {
        val diffCallback = GenresDiffCallback(genres, newGenres)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        genres.clear()
        genres.addAll(newGenres)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class GenresViewHolder(val binding: ItemGenresBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val binding = ItemGenresBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return GenresViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        val genre = genres[position]
        holder.binding.genresChip.text = genre
    }

    override fun getItemCount() = genres.size
}
