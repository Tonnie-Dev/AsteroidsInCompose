package com.uxstate.data.mapper

import com.uxstate.data.remote.dto.NearEarthObjectDTO
import org.json.JSONObject

fun parseJSONStringResponse(response: String): List<NearEarthObjectDTO> {
    val neowsObjects = mutableListOf<NearEarthObjectDTO>()
    val jsonObject = JSONObject(response)
    val nearEarthObjectsJson = jsonObject.getJSONObject("near_earth_objects")
    val dateList = nearEarthObjectsJson.keys()
    val dateListSorted = dateList.asSequence().sorted()
    dateListSorted.forEach {
        val key: String = it
        val dateAsteroidJsonArray = nearEarthObjectsJson.getJSONArray(key)
        for (i in 0 until dateAsteroidJsonArray.length()) {
            val asteroidJson = dateAsteroidJsonArray.getJSONObject(i)
            val id = asteroidJson.getLong("id")
            val codename = asteroidJson.getString("name")
            val absoluteMagnitude = asteroidJson.getDouble("absolute_magnitude_h")
            val estimatedDiameter = asteroidJson.getJSONObject("estimated_diameter")
                    .getJSONObject("kilometers").getDouble("estimated_diameter_max")
            val closeApproachData = asteroidJson
                    .getJSONArray("close_approach_data").getJSONObject(0)
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
                    absoluteMagnitude,
                    estimatedDiameter,
                    relativeVelocity,
                    distanceFromEarth,
                    isPotentiallyHazardous
            )
            neowsObjects.add(neowsObject)
        }
    }
    return neowsObjects
}
