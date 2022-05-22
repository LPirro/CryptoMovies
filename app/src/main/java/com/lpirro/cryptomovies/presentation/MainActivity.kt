package com.lpirro.cryptomovies.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lpirro.cryptomovies.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
