package com.example.eventsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventsapp.data.EventItem
import com.example.eventsapp.databinding.ActivityMainBinding
import com.example.eventsapp.view.EventAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // test
        val list = generateDummyList(500)

        // recycler
        binding.eventsRecyclerView.adapter = EventAdapter(list)
        binding.eventsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.eventsRecyclerView.setHasFixedSize(true)
    }

    private fun generateDummyList(size: Int): List<EventItem> {
        val list = ArrayList<EventItem>()

        for (i in 0 until size) {
            val item = EventItem("Item $i", "date")
            list += item
        }

        return list
    }

    fun saveEvent(){

    }

    fun deleteEvent(){

    }
}
