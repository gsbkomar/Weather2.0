package com.example.domain.domain.usecases

import com.example.domain.domain.entity.resultobjects.Forecasts
import com.example.domain.domain.repository.ForecastRepository
import javax.inject.Inject

class GetForecastsUseCase @Inject constructor(private val forecastRepository: ForecastRepository) {

    suspend fun execute(lat: Double, lon: Double): List<Forecasts> =
        forecastRepository.getForecastExecute(lat, lon).forecasts
}