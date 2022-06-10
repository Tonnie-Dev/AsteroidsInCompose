package com.uxstate.domain.use_cases

import com.uxstate.domain.model.AstroPicture
import com.uxstate.domain.repository.NeowsRepository
import com.uxstate.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetAstroPicturesUseCase(private val repository: NeowsRepository) {

    fun invoke(): Flow<Resource<List<AstroPicture>>> =
        //Return a flow builder to have access to emit()
        flow {

            //loading status = true
            emit(Resource.Loading(isLoading = true))

            //make api call

            val response = try {

                repository.getAstroPictures().filter { it.url=="image" }

            } catch (e: IOException) {

                e.printStackTrace()
                emit(
                        Resource.Error(
                                message = e.localizedMessage
                                    ?: "Couldn't reach server. Check your internet connection"
                        )
                )

                //return null data/response
                null

            } catch (e: HttpException) {

                emit(Resource.Error(message = e.localizedMessage ?: "Unexpected Error Happened"))

                //return null data/response
                null
            }

            //emit data wrapped in success resource
            emit(Resource.Success(data = response))

            //discontinue loading

            emit(Resource.Loading(isLoading = false))

        }
}