package com.example.weather20.presentation.translations

import android.app.Activity
import com.example.weather20.R
import com.example.weather20.data.dto.forecastdto.HoursDto

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

    fun timeInReadableForm(hours: HoursDto) = when(hours.hour) {
        "0" -> "01:00"
        "1" -> "02:00"
        "2" -> "03:00"
        "3" -> "04:00"
        "4" -> "05:00"
        "5" -> "06:00"
        "6" -> "07:00"
        "7" -> "08:00"
        "8" -> "09:00"
        "9" -> "10:00"
        "10" -> "11:00"
        "11" -> "12:00"
        "12" -> "13:00"
        "13" -> "14:00"
        "14" -> "15:00"
        "15" -> "16:00"
        "16" -> "17:00"
        "17" -> "18:00"
        "18" -> "19:00"
        "19" -> "20:00"
        "20" -> "21:00"
        "21" -> "22:00"
        "22" -> "23:00"
        else -> {"24:00"}
    }
}