package com.uxstate.data.remote

import com.uxstate.data.remote.dto.AstroPhotoDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface AstroAPI {

    @GET("planetary/apod")
    suspend fun getAstroPictures(@Query("count") count:Int = COUNT,
                                 @Query("api_key") apiKey: String = API_KEY

    ): List<AstroPhotoDTO>

    companion object {

        //base url is shared
        const val BASE_URL = "https://api.nasa.gov/"

        const val API_KEY = "Cnkc0VTeJQklSu79us3xzoRCmhjCIa9nsXpLdjs6"

        const val COUNT = 4

    }
}