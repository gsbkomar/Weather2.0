package com.example.weather20.domain

import com.example.weather20.data.RetrofitYandexWeatherInstance
import com.example.weather20.entity.Results
import javax.inject.Inject

class GetForecastUseCase @Inject constructor() {

    suspend fun getForecastExecute(lat: Double, lon: Double): Results {
        return RetrofitYandexWeatherInstance.RetrofitInstance.getForecast.getForecastProvider(lat, lon)
    }
}