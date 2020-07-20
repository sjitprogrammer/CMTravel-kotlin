package com.example.cmtravel.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cmtravel.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import kotlin.collections.ArrayList

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {
    val trips: ArrayList<Trips> = ArrayList()
    val tempItem: ArrayList<Trips> = ArrayList()
    val db = FirebaseFirestore.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData()
        searchView_trip.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText : String?): Boolean {
                Log.d(TAG,newText)
                if(newText!!.isNotEmpty()){
                    tempItem.clear()
                    val search = newText.toLowerCase(Locale.getDefault())

                    trips.forEach{
                        if(it.title?.toLowerCase(Locale.getDefault())?.contains(search)!!){
                            tempItem.add(it)
                        }
                    }
                    recyclerView_home.adapter?.notifyDataSetChanged()
                }else{
                    tempItem.clear()
                    tempItem.addAll(trips)
                    recyclerView_home.adapter?.notifyDataSetChanged()
                }
                return true
            }

        })
    }

    private fun fetchData() {

        db.collection("trips")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.d(TAG, document.id + " => " + document.data)
//                        val myObject = document.toObject(Trips::class.java)
                        trips.add(
                            Trips(
                                document.data["title"] as String,
                                document.data["description"] as String,
                                document.data["imageUrl"] as String,
                                document.data["location"] as GeoPoint,
                                (document.get("price") as Long).toInt()
                            )
                        )
                    }
                    showData(trips)
                    tempItem.addAll(trips)
                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun showData(trips: List<Trips>) {

        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView_home.layoutManager = gridLayoutManager
        recyclerView_home.adapter = TripsAdapter(tempItem, requireContext())
    }


}