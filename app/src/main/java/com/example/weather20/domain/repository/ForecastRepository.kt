package com.example.weather20.domain.repository

import com.example.weather20.data.dto.ResultsDto
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp


interface ForecastRepository  {
    suspend fun getForecastExecute(lat: Double, lon: Double): ResultsDto
}