package com.example.eventsapp.ui.events

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.eventsapp.data.EventEntity
import com.example.eventsapp.data.EventsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class EventsViewModel @ViewModelInject constructor(
    private val eventsRepository: EventsRepository,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {

    // getting list of events from db

    val events = Pager(
        PagingConfig(
            10,
            enablePlaceholders = true,
            maxSize = 200
        )
    ) {
        eventsRepository.getEvents()
    }.flow.flowOn(Dispatchers.IO)

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

    // save event
    private fun createEvent(event: EventEntity) = viewModelScope.launch(Dispatchers.IO) {
        eventsRepository.save(event)
    }

    // delete event
    fun onDeleteClick(it: EventEntity) = viewModelScope.launch(Dispatchers.IO) {
        eventsRepository.delete(it)
    }
}
