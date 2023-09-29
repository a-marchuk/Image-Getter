package com.example.imagegetter.presentation.interfaces

import com.example.imagegetter.data.room.Photo
import kotlinx.coroutines.flow.Flow

interface PhotoUiData {
    val photosFlow: Flow<List<Photo>>
}
