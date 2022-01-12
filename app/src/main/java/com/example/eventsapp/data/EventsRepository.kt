package com.example.eventsapp.data

import androidx.paging.PagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EventsRepository @Inject constructor(private val dao: EventDao) {

    fun getEvents(): PagingSource<Int, EventEntity> {
        return dao.getEvents()
    }

    suspend fun save(event: EventEntity) {
        withContext(Dispatchers.IO) {
            dao.save(event)
        }
    }

    suspend fun delete(event: EventEntity) {
        withContext(Dispatchers.IO) {
            dao.delete(event)
        }
    }
}
