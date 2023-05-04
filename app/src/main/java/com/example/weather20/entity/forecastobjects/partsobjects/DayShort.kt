package com.example.weather20.entity.forecastobjects.partsobjects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DayShort(
    @Json(name = "temp") val temp: Byte? = null,
    @Json(name = "temp_min") val temp_min: Byte? = null, // Минимальная температура для времени суток (°C).
    @Json(name = "icon") val icon: String? = null
)