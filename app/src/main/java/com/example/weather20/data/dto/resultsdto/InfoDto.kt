package com.example.weather20.data.dto.resultsdto

import com.example.weather20.data.dto.infodto.TzinfoDto
import com.example.weather20.entity.resultobjects.Info
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InfoDto(
    @Json(name = "tzinfo")
    override val tzinfo: TzinfoDto
) : Info