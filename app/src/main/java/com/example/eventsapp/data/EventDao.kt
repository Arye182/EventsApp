package com.example.eventsapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @androidx.room.Query("SELECT * FROM event_table")
    fun getEvents(): Flow<List<EventEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(event: EventEntity)

    @Delete
    suspend fun delete(event: EventEntity)
}
