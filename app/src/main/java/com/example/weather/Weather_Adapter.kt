package com.example.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.city_item.view.*

class Weather_Adapter (val list:MutableList<CityItem>):RecyclerView.Adapter<Weather_Adapter.RvView>(){


    class RvView(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvView {
        return RvView(
                LayoutInflater.from(parent.context).inflate(R.layout.city_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: RvView, position: Int) {
        val cityItem = list[position]
        holder.itemView.sity.text = cityItem.title
        holder.itemView.type.text = "type: "+ cityItem.location_type

    }

    override fun getItemCount(): Int = list.size
}
