package com.example.imagegetter.presentation

import androidx.lifecycle.ViewModel
import com.example.imagegetter.data.repository.PhotoRepository
import com.example.imagegetter.data.room.Photo
import com.example.imagegetter.presentation.interfaces.PhotoUiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val repository: PhotoRepository
) : ViewModel() {

    private val _photosFlow = MutableStateFlow<List<Photo>>(emptyList())
    val photosFlow: StateFlow<List<Photo>> = _photosFlow

    suspend fun getPhotos() {
        val photos = repository.getPhotos()
        _photosFlow.value = photos
    }

    suspend fun updatePhotos() {
        val newPhoto = repository.fetchAndSaveNewPhoto()
        _photosFlow.value = newPhoto
    }
}


