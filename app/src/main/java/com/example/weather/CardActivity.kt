package com.example.weather

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.city_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CardActivity : AppCompatActivity() {
    private val retrofitPogoda = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.metaweather.com/")
            .build()

    private val pogodaClient = retrofitPogoda.create(PogodaAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.city_weather)

        CityInCard.text = CityBuff.citybuff?.title

        val id = CityBuff.citybuff?.woeid

        if (id != null) {
            pogodaClient.pogoda(id).enqueue(object : Callback<WeatherResponse> {

                override fun onResponse(
                        call: Call<WeatherResponse>,
                        response: Response<WeatherResponse>

                ) {
                    println()
                    val pogodatemp: WeatherResponse? = response.body()
                    if (pogodatemp != null) {
                        temperatureInCard.text = pogodatemp.consolidated_weather[0].theTemp.toInt().toString() + "º"
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Log.e("CardActivity", t.message!!)
                }

            })

        }


    }
}