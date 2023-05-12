package com.example.domain.domain.entity

import com.example.domain.domain.entity.resultobjects.Fact
import com.example.domain.domain.entity.resultobjects.Forecasts
import com.example.domain.domain.entity.resultobjects.Info

interface Results {
    val info: Info
    val forecasts: List<Forecasts>
    val fact: Fact
}

