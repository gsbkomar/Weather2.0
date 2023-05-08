package com.example.weather20.data.dto.resultsdto

import com.example.weather20.entity.resultobjects.Fact
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FactDto (
    @Json(name = "temp")
    override val temp: Byte,
    @Json(name = "feels_like")
    override val feels_like: Byte,
    @Json(name = "icon")
    override val icon: String,
    @Json(name = "condition")
    override val condition: String,
    @Json(name = "wind_speed")
    override val wind_speed: Double,
    @Json(name = "wind_dir")
    override val wind_dir: String,
    @Json(name = "humidity")
    override val humidity: Byte,
    @Json(name = "prec_type")
    override val prec_type: Byte,
    @Json(name = "phenom_icon")
    override val phenom_icon: String? = null
) : Fact