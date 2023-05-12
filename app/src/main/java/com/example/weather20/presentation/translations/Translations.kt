package com.example.weather20.presentation.translations

import android.app.Activity
import com.example.data.dto.forecastdto.HoursDto
import com.example.weather20.R

class Translations {
    fun translateCondition(fact: String?, activity: Activity) = when (fact) {
        "clear" -> activity.getString(R.string.clear)
        "partly-cloudy" -> activity.getString(R.string.partly_cloudy)
        "cloudy" -> activity.getString(R.string.cloudy)
        "overcast" -> activity.getString(R.string.overcast)
        "drizzle" -> activity.getString(R.string.drizzle)
        "light-rain" -> activity.getString(R.string.light_rain)
        "rain" -> activity.getString(R.string.rain)
        "moderate-rain" -> activity.getString(R.string.moderate_rain)
        "heavy-rain" -> activity.getString(R.string.heavy_rain)
        "continuous-heavy-rain" -> activity.getString(R.string.continuous_heavy_rain)
        "showers" -> activity.getString(R.string.showers)
        "wet-snow" -> activity.getString(R.string.wet_snow)
        "light-snow" -> activity.getString(R.string.light_snow)
        "snow" -> activity.getString(R.string.snow)
        "snow-showers" -> activity.getString(R.string.snow_showers)
        "hail" -> activity.getString(R.string.hail)
        "thunderstorm" -> activity.getString(R.string.thunderstorm)
        "thunderstorm-with-rain" -> activity.getString(R.string.thunderstorm_with_rain)
        else -> {
            activity.getString(R.string.thunderstorm_with_hail)
        }
    }

    fun timeInReadableForm(hours: HoursDto) : String {
        val hours = hours.hour.toInt()
        return when {
            (hours < 9) -> "0${hours.plus(1)}:00"
            (hours < 20) -> "${hours.plus(1)}:00"
            (hours < 23) -> "${hours.plus(1)}:00"
            else -> {"00:00"}
        }
    }
}