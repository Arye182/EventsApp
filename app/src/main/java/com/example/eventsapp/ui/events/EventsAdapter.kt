package com.example.eventsapp.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eventsapp.data.EventEntity
import com.example.eventsapp.databinding.EventCardBinding

class EventsAdapter(private val eventsList: List<EventEntity>) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    class EventViewHolder(val binding: EventCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(EventCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {

        // current item from list
        val currentEventItem = eventsList[position]
        // update the item fields
        holder.binding.eventCardName.text = currentEventItem.name

        // what happens when press delete
        holder.binding.eventCardDeleteButton.setOnClickListener {
        }
    }

    override fun getItemCount() = eventsList.size
}
