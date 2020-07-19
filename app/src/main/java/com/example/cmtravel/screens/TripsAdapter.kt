package com.example.cmtravel.screens

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cmtravel.R
import com.squareup.picasso.Picasso

class TripsAdapter(private var items: List<Trips>, private val context: Context) :
    RecyclerView.Adapter<TripsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.trip_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
//        Glide
//            .with(context)
//            .load(item.imageUrl)
//            .centerCrop()
//            .placeholder(ColorDrawable(Color.BLACK))
//            .into(holder.imageView);
        Picasso.get().load(item.imageUrl).into(holder.imageView)
        holder.title.text = item.title.toString()
        holder.price.text = "฿" + item.price.toString()
        Log.e("onBindViewHolder", "" + item.title.toString())
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.icon_image_view)
        val title: TextView = view.findViewById(R.id.title_text_view)
        val price: TextView = view.findViewById(R.id.price_texView)
    }


}
