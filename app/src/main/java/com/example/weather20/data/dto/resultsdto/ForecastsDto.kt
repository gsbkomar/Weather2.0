package com.example.weather20.data.dto.resultsdto

import com.example.weather20.data.dto.forecastdto.HoursDto
import com.example.weather20.data.dto.forecastdto.PartsDto
import com.example.weather20.entity.forecastobjects.Parts
import com.example.weather20.entity.resultobjects.Forecasts
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastsDto (
    @Json(name = "date")
    override val date: String? = null,
    @Json(name = "parts")
    override val parts: PartsDto? = null,
    @Json(name = "hours")
    override val hours: List<HoursDto>,
    @Json(name = "week")
    override val week: Byte? = null,
    @Json(name = "temp_avg")
    override val temp_avg: Double? = null,
    @Json(name = "icon")
    override val icon: String? = null,
    @Json(name = "wind_speed")
    override val wind_speed: Double? = null,
    @Json(name = "prec_type")
    override val prec_type: Byte? = null,
    @Json(name = "prec_strength")
    override val prec_strength: Double? = null
) : Forecasts