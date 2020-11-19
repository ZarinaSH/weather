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
import kotlinx.android.synthetic.main.city_item.view.*
import java.security.AccessController.getContext


class CityAdapter(val list: MutableList<CityItem>):RecyclerView.Adapter<CityAdapter.RvView>(){


    class RvView(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvView {
        return RvView(LayoutInflater.from(parent.context).inflate(R.layout.city_item, parent, false))
    }

    override fun onBindViewHolder(holder: RvView, position: Int) {
        val cityItem = list[position]
        holder.itemView.sity.text = cityItem.title
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


