package com.example.weather20.entity.resultobjects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Fact(
   @Json(name="temp") val temp: Byte? = null, // Температура (°C).
   @Json(name="feels_like") val feels_like : Byte? = null, // Ощущаемая температура (°C).
   @Json(name="icon") val icon: String? = null, // Код иконки погоды.
   @Json(name="condition") val condition: String? = null, // Код расшифровки погодного описания.
   @Json(name="wind_speed") val wind_speed: Double? = null, // Скорость ветра (в м/с).
   @Json(name="wind_dir") val wind_dir: String? = null, // Направление ветра.
   @Json(name="humidity") val humidity: Byte? = null, // Влажность воздуха (в процентах).
   @Json(name="prec_type") val prec_type: Byte? = null, // Тип осадков.
  // @Json(name="prec_strength") val prec_strength: Number? = null, // Сила осадков.
   @Json(name="phenom_icon") val phenom_icon: String? = null, // Код дополнительной иконки погодного явления.

)

