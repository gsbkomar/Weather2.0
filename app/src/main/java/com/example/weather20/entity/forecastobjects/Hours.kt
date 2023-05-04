package com.example.weather20.entity.forecastobjects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hours(
    @Json(name="hour") val hour: String? = null,
    @Json(name="temp") val temp: Int? = null,
    @Json(name="feels_like") val feels_like: Byte? = null,
    @Json(name="icon") val icon: String? = null,
    @Json(name="condition") val condition: String? = null // String or Number
)