package com.lpirro.cryptomovies.presentation.details.adapter

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil

class GenresDiffCallback(
    private val oldList: List<String>,
    private val newList: List<String>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldCourse: Int, newPosition: Int): Boolean {
        return oldList[oldCourse] == newList[newPosition]
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}
