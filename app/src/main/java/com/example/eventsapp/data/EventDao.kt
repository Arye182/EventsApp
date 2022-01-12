package com.example.eventsapp.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface EventDao {

    @androidx.room.Query("SELECT * FROM event_table ORDER BY created DESC")
    fun getEvents(): PagingSource<Int, EventEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(event: EventEntity)

    @Delete
    suspend fun delete(event: EventEntity)
}
