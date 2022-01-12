package com.example.eventsapp.data

import androidx.paging.PagingSource
import javax.inject.Inject

class EventsRepository @Inject constructor(private val dao: EventDao) {

    fun getEvents(): PagingSource<Int, EventEntity> {
        return dao.getEvents()
    }

    suspend fun save(event: EventEntity) {
        return dao.save(event)
    }

    suspend fun delete(event: EventEntity) {
        return dao.delete(event)
    }
}
