package com.uxstate.di

import android.content.Context
import androidx.room.Room
import com.uxstate.data.local.NeowsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNeowsDatabase(@ApplicationContext context: Context): NeowsDatabase {

        return Room.databaseBuilder(context, NeowsDatabase::class.java, NeowsDatabase.DB_NAME)
                .build()

    }
}