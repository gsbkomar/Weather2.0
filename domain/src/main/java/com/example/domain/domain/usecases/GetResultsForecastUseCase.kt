package com.example.domain.domain.usecases

import com.example.domain.domain.entity.Results
import com.example.domain.domain.repository.ForecastRepository
import javax.inject.Inject

class GetResultsForecastUseCase @Inject constructor(private val forecastRepository: ForecastRepository) {
    suspend fun execute(lat: Double, lon: Double) : Results = forecastRepository.getForecastExecute(lat, lon)
}