package com.example.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.ArrayAdapter
import android.widget.SearchView
import com.example.search.databinding.ActivityMainBinding


class MainActivity() : AppCompatActivity(), Parcelable {

    lateinit var binding : ActivityMainBinding

    constructor(parcel: Parcel) : this() {

    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val user = arrayOf("aljabar", "matrix", "kapita selekta", "peluang", "volume ruang")
        val userAdapter : ArrayAdapter<String> = ArrayAdapter(
            this,android.R.layout.simple_list_item_1,
            user
        )

        binding.user.adapter = userAdapter;

        var onQueryTextListener =
            binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    binding.searchview.clearFocus()
                    if (user.contains(query)){

                        userAdapter.filter.filter(query)

                    }
                    return false
                }

                override fun onQueryTextChange(newText:String?): Boolean {
                    userAdapter.filter.filter(newText)
                    return false
                }

            })
}