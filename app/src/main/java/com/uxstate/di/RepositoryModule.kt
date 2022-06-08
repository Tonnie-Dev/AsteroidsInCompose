package com.uxstate.di

import com.uxstate.data.repository.NeowsRepositoryImpl
import com.uxstate.domain.repository.NeowsRepository
import dagger.Binds
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Singleton
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    //REPOSITORY
    @Binds //@Binds used for 1-to-1 interface-implementation mapping
    @Singleton

    abstract fun provideNeowsRepository(repositoryImpl: NeowsRepositoryImpl): NeowsRepository
}