package com.example.weather20.entity.forecastobjects.partsobjects

interface DayShort {
    val temp: Byte
    val temp_min: Byte
    val icon: String
    val condition: String?
    val feels_like: Byte
    val humidity: Byte
    val wind_speed: Double
}