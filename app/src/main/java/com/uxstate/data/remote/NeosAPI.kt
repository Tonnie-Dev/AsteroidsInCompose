package com.uxstate.data.remote

import com.uxstate.data.remote.dto.SomeDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NeosAPI {

    @GET("neo/rest/v1/feed")
    suspend fun getNearEarthObjects(

        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String

    ):List<SomeDTO>


    companion object {

        const val BASE_URL = "https://api.nasa.gov/"
        const val API_KEY = "Cnkc0VTeJQklSu79us3xzoRCmhjCIa9nsXpLdjs6"
    }
}