package com.example.weather20.domain.usecases

import com.example.weather20.data.ForecastRepositoryImpl
import com.example.weather20.data.dto.ResultsDto
import com.example.weather20.domain.repository.ForecastRepository
import javax.inject.Inject

class GetResultsForecastUseCase @Inject constructor(private val forecastRepository: ForecastRepository) {
    suspend fun execute(lat: Double, lon: Double) : ResultsDto = forecastRepository.getForecastExecute(lat, lon)
}