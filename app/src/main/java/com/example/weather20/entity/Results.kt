package com.example.weather20.entity

import com.example.weather20.entity.resultobjects.Fact
import com.example.weather20.entity.resultobjects.Forecasts
import com.example.weather20.entity.resultobjects.Info
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Results(
    @Json(name="info") val info: Info? = null, // Объект информации о населенном пункте.
    @Json(name="forecasts") val forecasts: List<Forecasts>? = null, // Объект прогнозной информации о погоде.
    @Json(name="fact") val fact: Fact? = null
)

