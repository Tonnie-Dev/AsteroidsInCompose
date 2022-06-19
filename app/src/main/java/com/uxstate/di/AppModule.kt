package com.uxstate.di

import android.app.Application
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.uxstate.data.local.AstroDatabase
import com.uxstate.data.remote.AstroPictureAPI
import com.uxstate.domain.repository.AstroRepository
import com.uxstate.domain.use_cases.GetAstroPicturesUseCase
import com.uxstate.domain.use_cases.GetNeowsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideNeowsDatabase(app: Application): AstroDatabase {

        return Room.databaseBuilder(app,
                AstroDatabase::class.java,
                AstroDatabase.DB_NAME)
                .build()

    }

    @Provides
    @Singleton
    fun provideNeowsAPI(): AstroPictureAPI {
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder()
                .baseUrl(AstroPictureAPI.BASE_URL)
                . addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(AstroPictureAPI::class.java)
    }

    @Provides
    @Singleton

    fun provideGetNeowsUseCase(repository: AstroRepository): GetNeowsUseCase {

        return GetNeowsUseCase(repository)
    }

    @Provides
    @Singleton

    fun provideGetAstroPicturesUseCase(repository: AstroRepository): GetAstroPicturesUseCase {

        return GetAstroPicturesUseCase(repository)
    }

}