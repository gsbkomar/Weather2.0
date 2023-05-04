package com.example.weather20.data

import com.example.weather20.Key
import com.example.weather20.entity.Results
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Inject

private const val BASE_URL = "https://api.weather.yandex.ru/v2/"

class RetrofitYandexWeatherInstance @Inject constructor() {

    object RetrofitInstance {
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val getForecast: GetForecastProvider = retrofit.create(GetForecastProvider::class.java)
    }

    interface GetForecastProvider {
        @Headers(
            "Accept: application/json",
            "Content-type: application/json",
            "X-Yandex-API-Key: ${Key.API_KEY}",
        )
        @GET("forecast?")
        suspend fun getForecastProvider(
            @Query("lat") lat: Double,
            @Query("lon") lon: Double,
            @Query("limit") limit: Byte = 7,
            @Query("extra") extra: Boolean = true
        ): Results
    }
}

