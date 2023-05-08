package com.example.weather20.entity

import com.example.weather20.entity.resultobjects.Fact
import com.example.weather20.entity.resultobjects.Forecasts
import com.example.weather20.entity.resultobjects.Info

interface Results {
    val info: Info
    val forecasts: List<Forecasts>
    val fact: Fact
}

