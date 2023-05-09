package com.example.weather20.domain.usecases

import com.example.weather20.data.ForecastRepository

import com.example.weather20.data.dto.resultsdto.ForecastsDto
import javax.inject.Inject

class GetForecastsUseCase @Inject constructor(private val forecastRepository: ForecastRepository) {

    suspend fun execute(lat: Double, lon: Double): List<ForecastsDto> =
        forecastRepository.getForecastExecute(lat, lon).forecasts
}