package com.uxstate.data.json

import com.uxstate.data.remote.dto.NearEarthObjectDTO

interface JsonParser {

    fun parseJson(jsonString: String):List<NearEarthObjectDTO>
}