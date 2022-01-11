package com.example.eventsapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eventsapp.data.EventItem
import com.example.eventsapp.databinding.EventCardBinding

class EventAdapter(private val eventsList: List<EventItem>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    class EventViewHolder(val binding: EventCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(EventCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {

        // current item from list
        val currentEventItem = eventsList[position]
        // update the item fields
        holder.binding.eventCardName.text = currentEventItem.name
        holder.binding.eventCardTime.text = currentEventItem.date

        // what happens when press delete
        holder.binding.eventCardDeleteButton.setOnClickListener {
        }
    }

    override fun getItemCount() = eventsList.size
}
