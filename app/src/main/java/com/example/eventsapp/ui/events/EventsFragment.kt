package com.example.eventsapp.ui.events

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventsapp.R
import com.example.eventsapp.databinding.FragmentEventsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EventsFragment : Fragment(R.layout.fragment_events) {

    private val viewModel: EventsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentEventsBinding.bind(view)

        val eventAdapter = EventsPagingDataAdapter{
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
        //yy@OptIn(ExperimentalCoroutinesApi::class)


        // observe view model list
            lifecycleScope.launch() {
                viewModel.events.collectLatest { source -> eventAdapter.submitData(source) }
            }
    }
}
