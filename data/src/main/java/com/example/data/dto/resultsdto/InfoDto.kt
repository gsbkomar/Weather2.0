package com.example.data.dto.resultsdto

import com.example.domain.domain.entity.resultobjects.Info
import com.example.data.dto.infodto.TzinfoDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InfoDto(
    @Json(name = "tzinfo")
    override val tzinfo: TzinfoDto
) : Info