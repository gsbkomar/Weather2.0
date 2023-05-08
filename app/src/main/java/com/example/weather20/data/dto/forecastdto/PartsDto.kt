package com.example.weather20.data.dto.forecastdto

import com.example.weather20.data.dto.forecastdto.partsdto.DayShortDto
import com.example.weather20.entity.forecastobjects.Parts
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PartsDto(
    @Json(name = "day_short")
    override val day_short: DayShortDto
    ) : Parts