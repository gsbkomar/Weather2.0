package com.example.data.dto.infodto

import com.example.domain.domain.entity.infoobjects.Tzinfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TzinfoDto (
    @Json(name = "offset")
    override val offset: Double,
    @Json(name = "name")
    override val name: String,
    @Json(name = "abbr")
    override val abbr: String
) : Tzinfo