package com.example.cmtravel.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.cmtravel.HomeActivity
import com.example.cmtravel.R
import kotlinx.android.synthetic.main.fragment_third.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_third, container, false)
//        val viewPager =  activity?.findViewById<ViewPager2>(R.id.viewPager2)

        view.textView_finish.setOnClickListener {
            onBoardingFinish()
            startActivity(Intent(requireContext(),HomeActivity::class.java))
        }
        return view
    }

    private fun onBoardingFinish() {
        val sharePref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val edit = sharePref.edit()
        edit.putBoolean("onBoardingFinish", true)
        edit.apply()
    }
}