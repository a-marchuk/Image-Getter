package com.example.imagegetter.di

import android.app.Application
import androidx.room.Room
import com.example.imagegetter.data.api.ApiClient
import com.example.imagegetter.data.repository.PhotoRepository
import com.example.imagegetter.data.room.PhotoDao
import com.example.imagegetter.data.room.PhotoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyModule {

    @Singleton
    @Provides
    fun providePhotoRepository(
        photoDao: PhotoDao,
        apiClient: ApiClient
    ): PhotoRepository {
        return PhotoRepository(photoDao, apiClient)
    }

    @Singleton
    @Provides
    fun providePhotoDao(application: Application): PhotoDao {
        return Room.databaseBuilder(
            application,
            PhotoDatabase::class.java,
            "photo_database"
        ).build().photoDao()
    }

    @Singleton
    @Provides
    fun provideApiClient(): ApiClient{
        return ApiClient()
    }
}