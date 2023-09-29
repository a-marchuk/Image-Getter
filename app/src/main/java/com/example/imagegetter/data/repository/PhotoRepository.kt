package com.example.imagegetter.data.repository

import com.example.imagegetter.data.api.ApiClient
import com.example.imagegetter.data.room.Photo
import com.example.imagegetter.data.room.PhotoDao
import com.example.imagegetter.data.room.toPhoto

class PhotoRepository(private val photoDao: PhotoDao, private val apiClient: ApiClient) {

    suspend fun getPhotos(): List<Photo> {
        val cachedPhotos = photoDao.getAllPhotos()

        return cachedPhotos.ifEmpty {
            val photoFromServer = apiClient.fetchRandomPhoto()?.toPhoto()
            if (photoFromServer != null) {
                photoDao.insertPhoto(photoFromServer)
            }
            photoDao.getAllPhotos()
        }
    }

    suspend fun fetchAndSaveNewPhoto(): List<Photo> {
        return try {
            val newPhoto = apiClient.fetchRandomPhoto()?.toPhoto()
            if (newPhoto != null) {
                photoDao.insertPhoto(newPhoto)
            }
            listOfNotNull(newPhoto)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }


}
