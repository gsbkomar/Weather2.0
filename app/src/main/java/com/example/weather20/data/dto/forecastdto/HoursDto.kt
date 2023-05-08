package com.example.weather20.data.dto.forecastdto

import com.example.weather20.entity.forecastobjects.Hours
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HoursDto (
        @Json(name = "hour")
        override val hour: String,
        @Json(name = "temp")
        override val temp: Int,
        @Json(name = "feels_like")
        override val feels_like: Byte,
        @Json(name = "icon")
        override val icon: String,
        @Json(name = "condition")
        override val condition: String
) : Hours