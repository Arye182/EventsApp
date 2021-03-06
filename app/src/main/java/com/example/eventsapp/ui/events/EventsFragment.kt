package com.example.eventsapp.ui.events

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventsapp.R
import com.example.eventsapp.databinding.FragmentEventsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsFragment : Fragment(R.layout.fragment_events) {

    private val viewModel: EventsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentEventsBinding.bind(view)

        val eventAdapter = EventsAdapter{
            // if click listener has been activated
            viewModel.onDeleteClick(it)
        }

        // View Binding!
        binding.apply {
            // recycler view
            eventsRecyclerView.apply{
                adapter = eventAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }

            // new event edit text
            newEventEditText.addTextChangedListener {
                viewModel.eventName = it.toString()
            }

            // save button
            buttonSaveEvent.setOnClickListener{
                viewModel.onSaveClick()

            }
        }

        // observer for view model list
        viewModel.events.observe(viewLifecycleOwner){
            eventAdapter.submitList(it)
        }
    }
}
