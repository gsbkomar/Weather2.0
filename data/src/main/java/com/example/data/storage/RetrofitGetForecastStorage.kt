package com.example.data.storage

import com.example.weather20.data.RetrofitYandexWeatherInstance
import com.example.weather20.data.dto.ResultsDto
import javax.inject.Inject

class RetrofitGetForecastStorage @Inject constructor(): GetForecastStorage {
    override suspend fun get(lat: Double, lon: Double): ResultsDto =
        RetrofitYandexWeatherInstance.RetrofitInstance.getForecast.getForecastProvider(lat, lon)
}