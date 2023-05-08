package com.example.weather20.domain

import com.example.weather20.data.ForecastRepository
import com.example.weather20.data.RetrofitYandexWeatherInstance
import com.example.weather20.data.dto.ResultsDto
import com.example.weather20.entity.Results
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(private val forecastRepository: ForecastRepository) {
    suspend fun getForecastInfo(lat: Double, lon: Double) : ResultsDto = forecastRepository.getForecastExecute(lat, lon)
}