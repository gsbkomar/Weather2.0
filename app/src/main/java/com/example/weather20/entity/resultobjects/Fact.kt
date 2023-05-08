package com.example.weather20.entity.resultobjects

interface Fact {
   val temp: Byte
   val feels_like: Byte
   val icon: String
   val condition: String
   val wind_speed: Double
   val wind_dir: String
   val humidity: Byte
   val prec_type: Byte
   val phenom_icon: String?
}

