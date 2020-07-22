package com.example.cmtravel.screens

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.cmtravel.R
import kotlinx.android.synthetic.main.fragment_trip_detail.*

class TripDetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val transition = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionInflater.from(activity).inflateTransition(R.transition.trips_transition)
        } else {
            TODO("VERSION.SDK_INT < KITKAT")
        }
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var item: Trips = arguments?.getParcelable("trip_args")!!
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView_place.transitionName = "image_${item.id}"
            textView_title.transitionName = "title_text_${item.id}"
            textView_price.transitionName = "price_text_${item.id}"
        }
        Glide.with(this)
            .load(item.imageUrl)
            .placeholder(R.drawable.placeholder_image)
            .into(imageView_place);
        textView_title.text = item.title
        textView_desc.text = item.description
        textView_price.text = "${item.price}฿"

        imageView_back.setOnClickListener {
            findNavController().navigate(R.id.action_tripDetailFragment_to_homeFragment)
        }
    }
}