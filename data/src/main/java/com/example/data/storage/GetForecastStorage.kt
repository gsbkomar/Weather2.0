package com.example.data.storage

import com.example.weather20.data.dto.ResultsDto

interface GetForecastStorage {

    suspend fun get(lat: Double, lon: Double): ResultsDto
}