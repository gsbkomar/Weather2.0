package com.example.weather20.entity.forecastobjects.partsobjects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NightShort(
    @Json(name="temp") val temp: Byte? = null,
)

/*
"night_short": {
    "temp": 20,
    "feels_like": 22,
    "icon": "bkn_n",
    "condition": "cloudy",
    "wind_speed": 0.9,
    "wind_gust": 5.9,
    "wind_dir": "nw",
    "pressure_mm": 746,
    "pressure_pa": 995,
    "humidity": 81,
    "prec_type": 0,
    "prec_strength": 0,
    "cloudness": 0.75,
}*/