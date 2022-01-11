package com.example.eventsapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat

@Entity(tableName = "event_table")
data class EventEntity(
    val name: String,
    val created: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) {
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)
}
