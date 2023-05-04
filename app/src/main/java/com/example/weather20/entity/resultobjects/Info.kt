package com.example.weather20.entity.resultobjects

import com.example.weather20.entity.infoobjects.Tzinfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Info(
    @Json(name="tzinfo") val tzinfo: Tzinfo? = null // Информация о часовом поясе.

)

