package com.example.weather20.entity.resultobjects

import com.example.weather20.entity.forecastobjects.Parts
import com.example.weather20.entity.forecastobjects.Hours
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Forecasts(
    @Json(name = "date") val date: String? = null, // Дата прогноза в формате ГГГГ-ММ-ДД.
    @Json(name = "parts") val parts: Parts? = null,
    @Json(name = "hours") val hours: List<Hours>? = null,
    @Json(name = "temp_avg") val temp_avg: Double? = null, // Средняя температура для времени суток (°C).*/
    @Json(name = "icon") val icon: String? = null, // Код иконки погоды.*/
    @Json(name = "condition") val condition: String? = null, // Код расшифровки погодного описания.*/
    @Json(name = "wind_speed") val wind_speed: Double? = null, // Скорость ветра (в м/с).*/
    @Json(name = "prec_type") val prec_type: Byte? = null, // Тип осадков.*/
    @Json(name="prec_strength") val prec_strength: Double? = null // Сила осадков.*/
)

