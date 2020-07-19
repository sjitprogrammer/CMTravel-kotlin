package com.example.cmtravel.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.cmtravel.R
import kotlinx.android.synthetic.main.fragment_first.view.*

class FirstFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager2)

        view.textView_Next.setOnClickListener {
            viewPager?.currentItem = 1
        }

        return view
    }
}