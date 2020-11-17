package com.example.weather


import retrofit2.Call
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


//https://www.metaweather.com/api/location/2122265


/*

    https://www.metaweather.com/api/location/(woeid)/?=2122265&country=321

 */