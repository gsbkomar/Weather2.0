package com.example.weather20.data

import com.example.weather20.data.dto.ResultsDto
import com.example.weather20.domain.repository.ForecastRepository
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor() : ForecastRepository{
   override suspend fun getForecastExecute(lat: Double, lon: Double): ResultsDto {
      return RetrofitYandexWeatherInstance.RetrofitInstance.getForecast.getForecastProvider(lat, lon)
   }
}