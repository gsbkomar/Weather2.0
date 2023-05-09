package com.example.weather20.domain

import com.example.weather20.data.ForecastRepository
import com.example.weather20.data.dto.ResultsDto
import javax.inject.Inject

class GetResultsForecastUseCase @Inject constructor(private val forecastRepository: ForecastRepository) {
    suspend fun execute(lat: Double, lon: Double) : ResultsDto = forecastRepository.getForecastExecute(lat, lon)
}