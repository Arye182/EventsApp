package com.example.eventsapp.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.eventsapp.data.EventEntity
import com.example.eventsapp.databinding.EventCardBinding

class EventsPagingDataAdapter(private val onItemClicked: (EventEntity) -> Unit) :
    PagingDataAdapter<EventEntity, EventsPagingDataAdapter.EventViewHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val currentEvent = getItem(position)
        if (currentEvent != null) {
            holder.bind(currentEvent)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventsPagingDataAdapter.EventViewHolder {
        val binding = EventCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    inner class EventViewHolder(private val binding: EventCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
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
        override fun areItemsTheSame(oldItem: EventEntity, newItem: EventEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: EventEntity, newItem: EventEntity) =
            oldItem == newItem
    }
}
