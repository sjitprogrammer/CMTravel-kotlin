package com.example.cmtravel.screens

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cmtravel.R

class TripsAdapter (val items:List<Trips>,val context:Context) : RecyclerView.Adapter<TripsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.trip_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
//        Picasso.get().load(item.imageUrl).into(holder.imageView);
        Glide.with(context)
            .load(item.imageUrl)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.placeholder_image)
            .into(holder.imageView);
        holder.text_title.text = item.title
        holder.text_price.text = "฿ "+item.price.toString()

        holder.itemView.setOnClickListener {
            Log.e("TripsAdapter",item.title)
        }

    }

    class ViewHolder(val view: View) :RecyclerView.ViewHolder(view){
        val imageView:ImageView = view.findViewById(R.id.icon_image_view)
        val text_title:TextView = view.findViewById(R.id.title_text_view)
        val text_price:TextView = view.findViewById(R.id.price_texView)
    }
}

