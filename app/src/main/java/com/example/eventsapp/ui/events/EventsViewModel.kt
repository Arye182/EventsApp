package com.example.eventsapp.ui.events

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.eventsapp.data.EventDao

class EventsViewModel @ViewModelInject constructor(
    private val eventDao: EventDao
) : ViewModel(){

}
