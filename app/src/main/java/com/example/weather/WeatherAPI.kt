package com.example.weather


import android.graphics.drawable.Drawable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherAPI {
    @GET("/api/location/search/")
    fun searchSity(@Query("query") query: String): Call<List<CityItem>>

}

interface PogodaAPI {
    @GET("/api/location/{cityid}")
    fun pogoda(@Path("cityid") cityid: Int): Call<WeatherResponse>
}

