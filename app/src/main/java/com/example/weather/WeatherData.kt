package com.example.weather

import com.google.gson.annotations.SerializedName
import java.time.DateTimeException
import java.time.format.DateTimeFormatter

data class WeatherData(
    @SerializedName("the_temp")
    val theTemp: Double,
    val humidity: Double,
    val visibility: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerializedName("weather_state_abbr")
    val weatherStateAbbr: String,
    @SerializedName("weather_state_name")
    val weatherStateName:String
)

