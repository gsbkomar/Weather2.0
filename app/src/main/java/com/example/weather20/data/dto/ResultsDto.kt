package com.example.weather20.data.dto

import com.example.weather20.data.dto.resultsdto.FactDto
import com.example.weather20.data.dto.resultsdto.ForecastsDto
import com.example.weather20.data.dto.resultsdto.InfoDto
import com.example.weather20.entity.Results
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultsDto (
    @Json(name = "info")
    override val info: InfoDto,
    @Json(name = "forecasts")
    override val forecasts: List<ForecastsDto>,
    @Json(name = "fact")
    override val fact: FactDto
) : Results