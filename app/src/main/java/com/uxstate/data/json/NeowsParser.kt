package com.uxstate.data.mapper

import com.uxstate.data.json.JsonParser
import com.uxstate.data.remote.dto.NearEarthObjectDTO
import com.uxstate.domain.model.NearEarthObject
import org.json.JSONObject
import javax.inject.Inject

//https://api.nasa.gov/neo/rest/v1/feed?start_date=2015-09-07&end_date=2015-09-08&api_key=DEMO_KEY
class NeowsParser @Inject constructor() : JsonParser<NearEarthObjectDTO> {
    override fun parseJson(jsonString: String): List<NearEarthObjectDTO> {

        //create an empty list of Near Earth Objects
        val neowObjects = mutableListOf<NearEarthObjectDTO>()


        //get the entire or main/head json object by passing the entire string
        val jsonObject = JSONObject(jsonString)

        //get the sub/mid json object by passing it key
        val nearEarthObjectsJson = jsonObject.getJSONObject("near_earth_objects")

        //get the sub-objects for nearEarthObjectsJson by keys()
        val dateList = nearEarthObjectsJson.keys()

        //sort dates(sort is an intermediate ops so is excuated lazily)

        val dateListSorted = dateList.asSequence()
                .sorted()

        //iterate through date keys

        dateListSorted.forEach {

            val key = it

            //get sub-array under date
            val dateAsteroidJsonArray = nearEarthObjectsJson.getJSONArray(key)

            for (i in 0 until dateAsteroidJsonArray.length()) {
                val asteroidJson = dateAsteroidJsonArray.getJSONObject(i)
                val id = asteroidJson.getLong("id")
                val codename = asteroidJson.getString("name")

                val estimatedDiameter = asteroidJson.getJSONObject("estimated_diameter")
                        .getJSONObject("kilometers")
                        .getDouble("estimated_diameter_max")
                val closeApproachData = asteroidJson
                        .getJSONArray("close_approach_data")
                        .getJSONObject(0)
                val relativeVelocity = closeApproachData.getJSONObject("relative_velocity")
                        .getDouble("kilometers_per_second")
                val distanceFromEarth = closeApproachData.getJSONObject("miss_distance")
                        .getDouble("astronomical")
                val isPotentiallyHazardous = asteroidJson
                        .getBoolean("is_potentially_hazardous_asteroid")
                val neowsObject = NearEarthObjectDTO(
                        id,
                        codename,
                        key,
                        estimatedDiameter,
                        relativeVelocity,
                        distanceFromEarth,
                        isPotentiallyHazardous
                )
                neowObjects.add(neowsObject)

            }
        }

return neowObjects
    }


}

