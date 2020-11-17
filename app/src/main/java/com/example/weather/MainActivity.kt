package com.example.weather

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.city_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.metaweather.com/")
            .build()

    val weatherClient = retrofit.create(WeatherAPI::class.java)

    private lateinit var cityAdapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.layoutManager = LinearLayoutManager(this)

        var list:MutableList<CityItem> = ArrayList()

        cityAdapter = CityAdapter(list)
        rv.adapter = cityAdapter

        searchh.addTextChangedListener { editableText: Editable? ->

            if (editableText != null)
            {
                val text = editableText.toString()
                weatherClient.searchSity(text).enqueue(object :Callback<List<CityItem>>{

                    override fun onResponse(
                        call: Call<List<CityItem>>,
                        response: Response<List<CityItem>>
                    ) {println()
                        val cityItems = response.body()
                        if (cityItems!=null)
                        {
                          cityAdapter.addCity(cityItems)
                        }
                    }

                    override fun onFailure(call: Call<List<CityItem>>, t: Throwable) {
                        println()
                    }
                })
            }
        }


    }

}
