package com.example.weather20.data

import com.example.weather20.domain.GetForecastUseCase
import com.example.weather20.entity.Results
import javax.inject.Inject

class ForecastRepository @Inject constructor(private val getForecastUseCase: GetForecastUseCase) {
   suspend fun getForecastInfo(lat: Double, lon: Double) : Results = getForecastUseCase.getForecastExecute(lat, lon)
}