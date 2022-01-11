package com.example.eventsapp.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eventsapp.data.EventEntity
import com.example.eventsapp.databinding.EventCardBinding

class EventsAdapter(private val onItemClicked: (EventEntity) -> Unit) : ListAdapter<EventEntity, EventsAdapter.EventViewHolder >(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = EventCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val currentEvent = getItem(position)
        holder.bind(currentEvent)
    }

    inner class EventViewHolder(private val binding: EventCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: EventEntity) {
            binding.apply {
                eventCardName.text = event.name
                eventCardTime.text = event.createdDateFormatted
                // set the listener of delete button
                eventCardDeleteButton.setOnClickListener { onItemClicked(event) }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<EventEntity>() {
        override fun areItemsTheSame(oldItem: EventEntity, newItem: EventEntity) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: EventEntity, newItem: EventEntity) = oldItem == newItem
    }
}

// class EventsAdapter(private val eventsList: List<EventEntity>) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {
//
//    class EventViewHolder(val binding: EventCardBinding) : RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
//        return EventViewHolder(EventCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
//    }
//
//    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
//
//        // current item from list
//        val currentEventItem = eventsList[position]
//        // update the item fields
//        holder.binding.eventCardName.text = currentEventItem.name
//
//        // what happens when press delete
//        holder.binding.eventCardDeleteButton.setOnClickListener {
//        }
//    }
//
//    override fun getItemCount() = eventsList.size
// }
