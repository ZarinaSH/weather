package com.example.weather

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.system.Os.listen
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.city_item.view.*
import kotlinx.android.synthetic.main.city_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.AccessController.getContext


class CityAdapter(val list: MutableList<CityItem>):RecyclerView.Adapter<CityAdapter.RvView>(){
    val retrofitPogodaItem = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://www.metaweather.com/")
        .build()
    val pogodaClientItem = retrofitPogodaItem.create(PogodaAPI::class.java)

    class RvView(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvView {
        return RvView(LayoutInflater.from(parent.context).inflate(R.layout.city_item, parent, false))
    }

    override fun onBindViewHolder(holder: RvView, position: Int) {
        val cityItem = list[position]
        holder.itemView.sity.text = cityItem.title

        pogodaClientItem.pogoda(cityItem.woeid).enqueue(object : Callback<WeatherResponse> {

            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                val pogodatemp: WeatherResponse? = response.body()
                if (pogodatemp != null) {
                    holder.itemView.itemTemp.text = pogodatemp.consolidated_weather[0].theTemp.toInt().toString()
                }
            }
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
            }
        })

        holder.itemView.setOnClickListener{
                CityBuff.citybuff = cityItem
                  var intent = Intent(holder.itemView.sity.context,CardActivity::class.java)
                  holder.itemView.sity.getContext().startActivity(intent)

        }
    }

    override fun getItemCount(): Int = list.size

    fun addCity(cityItems: List<CityItem>){
        list.clear()
        list.addAll(cityItems)
        notifyDataSetChanged()
    }

}


