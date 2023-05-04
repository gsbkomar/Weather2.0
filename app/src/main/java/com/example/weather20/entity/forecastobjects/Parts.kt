package com.example.weather20.entity.forecastobjects

import com.example.weather20.entity.forecastobjects.partsobjects.DayShort
import com.example.weather20.entity.forecastobjects.partsobjects.NightShort
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Parts(
    @Json(name="night_short") val night_short: NightShort? = null, // прогноз на ночь.
   /* @Json(name="morning") val morning: Double? = null, //  прогноз на утро.*/
    @Json(name="day_short") val day_short: DayShort? = null,
     // Максимальная температура для времени суток (°C).// прогноз на день.
/*    @Json(name="evening") val evening: Double? = null // прогноз на вечер.*/
)

