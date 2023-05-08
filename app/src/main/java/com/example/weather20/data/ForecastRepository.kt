package com.example.weather20.data

import com.example.weather20.data.dto.ResultsDto
import com.example.weather20.domain.GetForecastUseCase
import com.example.weather20.entity.Results
import javax.inject.Inject

class ForecastRepository @Inject constructor() {
   suspend fun getForecastExecute(lat: Double, lon: Double): ResultsDto {
      return RetrofitYandexWeatherInstance.RetrofitInstance.getForecast.getForecastProvider(lat, lon)
   }
}