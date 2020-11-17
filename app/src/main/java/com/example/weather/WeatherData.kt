package com.example.weather

import com.google.gson.annotations.SerializedName
import java.time.DateTimeException
import java.time.format.DateTimeFormatter

data class WeatherData(
        @SerializedName("the_temp")
        val theTemp:Double
)

