package com.example.weather20.entity.forecastobjects

interface Hours {
    val hour: String
    val temp: Int
    val feels_like: Byte
    val icon: String
    val condition: String
}