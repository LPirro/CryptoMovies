package com.lpirro.cryptomovies.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.lpirro.cryptomovies.databinding.ErrorViewSmallBinding

class ErrorViewSmall @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding = ErrorViewSmallBinding.inflate(LayoutInflater.from(context), this, true)
}
