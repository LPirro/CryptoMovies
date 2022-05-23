package com.lpirro.cryptomovies.presentation.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.lpirro.cryptomovies.R
import com.lpirro.cryptomovies.databinding.ItemCastBinding

class CastAdapter : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    private val castList = arrayListOf<String>()

    fun setData(newCastList: List<String>) {
        val diffCallback = GenresDiffCallback(castList, newCastList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        castList.clear()
        castList.addAll(newCastList)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class CastViewHolder(val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding = ItemCastBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val castImage = castList[position]
        Glide.with(holder.itemView.context)
            .load(castImage)
            .circleCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.ic_cast_placeholder)
            .placeholder(R.drawable.ic_cast_placeholder)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(holder.binding.castImage)
    }

    override fun getItemCount() = castList.size
}
