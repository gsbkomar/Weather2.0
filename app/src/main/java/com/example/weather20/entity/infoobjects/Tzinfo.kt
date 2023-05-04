package com.example.weather20.entity.infoobjects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Tzinfo(
    @Json(name="offset") val offset: Double? = null, // Часовой пояс в секундах от UTC.
    @Json(name="name") val name: String? = null, // Название часового пояса.
    @Json(name="abbr") val abbr: String? = null // Сокращенное название часового пояса.
)
