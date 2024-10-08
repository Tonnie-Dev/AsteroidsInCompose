package com.uxstate.di

import android.app.Application
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.uxstate.data.local.AstroDatabase
import com.uxstate.data.remote.AstroAPI
import com.uxstate.domain.repository.AstroRepository
import com.uxstate.domain.use_cases.*
import com.uxstate.presentation.overview_screen.ConnectionLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideAstroDatabase(app: Application): AstroDatabase {
        return Room.databaseBuilder(
                app,
                AstroDatabase::class.java,
                AstroDatabase.DB_NAME
        ).fallbackToDestructiveMigration()
                .build()

    }

    @Provides
    @Singleton
    fun provideAstroAPI(): AstroAPI {

        val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

        return Retrofit.Builder()
                .baseUrl(AstroAPI.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create()
    }


    @Provides
    @Singleton
    fun provideUseCaseContainer(
        repository: AstroRepository
    ): UseCaseContainer {

        return UseCaseContainer(
                deleteFavoritePhotoUseCase = DeleteFavoritePhotoUseCase(repository),
                getAstroPhotosUseCase = GetAstroPhotosUseCase(repository),
                getFavAstroPhotosUseCase = GetFavAstroPhotosUseCase(repository),
                insertAstroPhotoUseCase =InsertAstroPhotoUseCase(repository),
                updateIsFavoriteStatus = UpdateIsFavoriteStatusUseCase(repository),
                getLiveAstroPhotosUseCase = GetLiveAstroPhotosUseCase(repository)
        )
    }


    @Provides
    @Singleton
    fun provideConnectionLiveData(app: Application): ConnectionLiveData{

        return ConnectionLiveData(app)
    }

}