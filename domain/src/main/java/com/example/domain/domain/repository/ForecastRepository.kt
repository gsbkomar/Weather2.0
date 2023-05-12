package com.example.domain.domain.repository

import com.example.domain.domain.entity.Results

interface ForecastRepository  {
    suspend fun getForecastExecute(lat: Double, lon: Double): Results
}