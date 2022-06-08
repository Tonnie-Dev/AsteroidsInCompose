package com.uxstate.di

import com.uxstate.data.json.JsonParser
import com.uxstate.data.mapper.NeowsParser
import com.uxstate.data.remote.dto.NearEarthObjectDTO
import com.uxstate.data.repository.NeowsRepositoryImpl
import com.uxstate.domain.repository.NeowsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    //REPOSITORY
    @Binds //@Binds used for 1-to-1 interface-implementation mapping
    @Singleton

    /*The abstract function takes only a single parameter which
    is the interface implementation and the return type is the
    interface implemented by the given parameter object.*/

    abstract fun provideNeowsRepository(repositoryImpl: NeowsRepositoryImpl): NeowsRepository

    @Binds//@Binds used for 1-to-1 interface-implementation mapping
    @Singleton

    /*The abstract function takes only a single parameter which
     is the interface implementation and the return type is the
     interface implemented by the given parameter object.*/

    abstract fun provideJsonParser(jsonParser: JsonParser<NearEarthObjectDTO>):NeowsParser
}