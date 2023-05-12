package com.example.data.repository

import com.example.domain.domain.repository.ForecastRepository
import com.example.weather20.data.dto.ResultsDto
import com.example.data.storage.RetrofitGetForecastStorage
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(private val retrofitGetForecastStorage: RetrofitGetForecastStorage) : ForecastRepository {
   override suspend fun getForecastExecute(lat: Double, lon: Double): ResultsDto = retrofitGetForecastStorage.get(lat, lon)
}