package com.uxstate.data.json

import com.uxstate.data.remote.dto.NearEarthObjectDTO

interface JsonParser <T>{

    fun parseJson(jsonString: String):List<T>
}