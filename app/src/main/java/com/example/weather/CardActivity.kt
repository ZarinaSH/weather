package com.example.weather

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.city_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CardActivity : AppCompatActivity() {
     val retrofitPogoda = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.metaweather.com/")
            .build()
     val pogodaClient = retrofitPogoda.create(PogodaAPI::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.city_weather)

        CityInCard.text = CityBuff.citybuff?.title

        val id = CityBuff.citybuff?.woeid

        if (id != null) {
            pogodaClient.pogoda(id).enqueue(object : Callback<WeatherResponse> {

                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>)
                {
                    val pogodatemp: WeatherResponse? = response.body()
                    if (pogodatemp != null)
                    {
                        temperatureInCard.text = pogodatemp.consolidated_weather[0].theTemp.toInt().toString() + "º"
                        visibility.text = pogodatemp.consolidated_weather[0].visibility.toInt().toString()+"%"
                        humidity.text = pogodatemp.consolidated_weather[0].humidity.toInt().toString()+"km"
                        how.text = pogodatemp.consolidated_weather[0].weatherStateName

                        wind.text = pogodatemp.consolidated_weather[0].windSpeed.toInt().toString()+"m/h"
                        //imageViewwWeather.
                        val bufferstring = pogodatemp.consolidated_weather[0].weatherStateAbbr
                        Glide.with(imageViewwWeather.context).load("https://www.metaweather.com//static/img/weather/png/64/"+bufferstring+".png").into(imageViewwWeather)



                        //Glide.with(holder.itemView.context).load(newnews.foto).into(holder.itemView.fotoo)


                    }
                }
                override fun onFailure(call: Call<WeatherResponse>, t: Throwable)
                {

                }
            })

        }
    }
}
