package com.example.cmtravel.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cmtravel.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {
    val tript: ArrayList<Trips> = ArrayList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = FirebaseFirestore.getInstance()

        var recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL ,false)
        val adapter = TripsAdapter(tript, requireContext())
        recyclerView.adapter = adapter

        db.collection("trips")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.d(TAG, document.id + " => " + document.data)
//                        val myObject = document.toObject(Trips::class.java)
                        tript.add(
                            Trips(
                                document.data["title"] as String,
                                document.data["description"] as String,
                                document.data["imageUrl"] as String,
                                document.data["location"] as GeoPoint,
                                (document.get("price") as Long).toInt()
                            )
                        )
                    }

                    adapter.notifyDataSetChanged()
                    Log.e(TAG,""+adapter)
                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }


}