package com.example.imagegetter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.imagegetter.data.room.Photo
import com.example.imagegetter.databinding.ActivityMainBinding
import com.example.imagegetter.viewmodels.PhotoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: PhotoViewModel by viewModels<PhotoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            viewModel.photosFlow.collect { photos ->
                updateUI(photos)
            }
        }
    }
    private fun updateUI(photos: List<Photo>) {
        
    }
}