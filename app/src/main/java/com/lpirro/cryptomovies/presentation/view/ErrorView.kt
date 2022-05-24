package com.lpirro.cryptomovies.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.lpirro.cryptomovies.databinding.ErrorViewBinding

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ErrorViewBinding.inflate(LayoutInflater.from(context), this, true)

    var retryClickListener: (() -> Unit)? = null

    init {
        binding.retryButton.setOnClickListener { retryClickListener?.invoke() }
    }
}
