package com.example.weather20.entity.resultobjects

import com.example.weather20.entity.forecastobjects.Parts
import com.example.weather20.entity.forecastobjects.Hours

interface Forecasts {
    val date: String?
    val parts: Parts?
    val hours: List<Hours>
    val week: Byte?
    val temp_avg: Double?
    val icon: String?
    val wind_speed: Double?
    val prec_type: Byte?
    val prec_strength: Double?
}

