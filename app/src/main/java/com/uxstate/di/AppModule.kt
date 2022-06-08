package com.uxstate.di

import android.content.Context
import androidx.room.Room
import com.uxstate.data.local.NeowsDatabase
import com.uxstate.data.remote.NeowsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideNeowsDatabase(@ApplicationContext context: Context): NeowsDatabase {

        return Room.databaseBuilder(context, NeowsDatabase::class.java, NeowsDatabase.DB_NAME)
                .build()

    }

    @Provides
    @Singleton
    fun provideNeowsAPI(): NeowsAPI {

        return Retrofit.Builder()
                .baseUrl(NeowsAPI.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(NeowsAPI::class.java)
    }

}