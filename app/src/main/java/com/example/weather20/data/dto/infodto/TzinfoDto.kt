package com.example.weather20.data.dto.infodto

import com.example.weather20.entity.infoobjects.Tzinfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TzinfoDto (
    @Json(name = "offset")
    override val offset: Double,
    @Json(name = "name")
    override val name: String,
    @Json(name = "abbr")
    override val abbr: String
) : Tzinfo