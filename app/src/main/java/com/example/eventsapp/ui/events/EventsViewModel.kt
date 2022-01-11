package com.example.eventsapp.ui.events

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.eventsapp.data.EventDao
import com.example.eventsapp.data.EventEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventsViewModel @ViewModelInject constructor(
    private val eventDao: EventDao,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {

    // getting list of events from db
    val events = eventDao.getEvents().asLiveData()

    // current event to be saved
    val event = state.get<EventEntity>("event")

    // event to be saved name
    var eventName = state.get<String>("eventName") ?: event?.name ?: ""
        set(value) {
            field = value
            state.set("eventName", value)
        }

    // save button on click
    fun onSaveClick() {
        if (eventName.isBlank()) {
            // show invalid input
        }

        val newEvent = EventEntity(name = eventName)
        createEvent(newEvent)

    }

    private fun createEvent(event : EventEntity) = viewModelScope.launch(Dispatchers.IO) {
        eventDao.save(event)
    }

    private fun deleteEvent(event: EventEntity) = viewModelScope.launch(Dispatchers.IO){

    }
}
