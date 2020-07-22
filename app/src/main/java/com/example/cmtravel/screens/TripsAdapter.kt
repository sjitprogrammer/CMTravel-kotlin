package com.example.cmtravel.screens

import android.content.Context
import android.os.Build
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

class TripsAdapter (val items:List<Trips>,val context:Context,val tripListener: TripListener) : RecyclerView.Adapter<TripsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.trip_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.imageView.transitionName = "image_${item.id}"
            holder.text_title.transitionName = "title_text_${item.id}"
            holder.text_price.transitionName = "price_text_${item.id}"
        }
        Glide.with(context)
            .load(item.imageUrl)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.placeholder_image)
            .into(holder.imageView);
        holder.text_title.text = item.title
        holder.text_price.text = "฿ ${item.price}.-"

        holder.itemView.setOnClickListener {
            tripListener.onClickTripListener(it,position)
        }

    }

    class ViewHolder(val view: View) :RecyclerView.ViewHolder(view){
        val imageView:ImageView = view.findViewById(R.id.icon_image_view)
        val text_title:TextView = view.findViewById(R.id.title_text_view)
        val text_price:TextView = view.findViewById(R.id.price_text_view)
    }
}

