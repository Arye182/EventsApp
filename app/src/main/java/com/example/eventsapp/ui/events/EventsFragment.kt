package com.example.eventsapp.ui.events

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.eventsapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsFragment : Fragment(R.layout.fragment_events) {

    private val viewModel: EventsViewModel by viewModels()


}
