package com.example.weather20.data.dto.forecastdto.partsdto

import com.example.weather20.entity.forecastobjects.partsobjects.DayShort
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DayShortDto (
    @Json(name = "temp")
    override val temp: Byte,
    @Json(name = "temp_min")
    override val temp_min: Byte,
    @Json(name = "icon")
    override val icon: String,
    @Json(name = "condition")
    override val condition: String? = null,
    @Json(name = "feels_like")
    override val feels_like: Byte,
    @Json(name = "humidity")
    override val humidity: Byte,
    @Json(name = "wind_speed")
    override val wind_speed: Double,
) : DayShort