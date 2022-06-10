package com.uxstate.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.uxstate.data.local.NeowsDatabase
import com.uxstate.data.remote.NeowsAPI
import com.uxstate.domain.repository.NeowsRepository
import com.uxstate.domain.use_cases.GetAstroPicturesUseCase
import com.uxstate.domain.use_cases.GetNeowsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideNeowsDatabase(app: Application): NeowsDatabase {

        return Room.databaseBuilder(app,
                NeowsDatabase::class.java,
                NeowsDatabase.DB_NAME)
                .build()

    }

    @Provides
    @Singleton
    fun provideNeowsAPI(): NeowsAPI {

        return Retrofit.Builder()
                .baseUrl(NeowsAPI.BASE_URL)
                . addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(NeowsAPI::class.java)
    }

    @Provides
    @Singleton

    fun provideGetNeowsUseCase(repository: NeowsRepository): GetNeowsUseCase {

        return GetNeowsUseCase(repository)
    }

    @Provides
    @Singleton

    fun provideGetAstroPicturesUseCase(repository: NeowsRepository): GetAstroPicturesUseCase {

        return GetAstroPicturesUseCase(repository)
    }

}