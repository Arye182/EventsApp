package com.example.eventsapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat

@Entity(tableName = "event_table")
data class EventEntity(

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "created")
    val created: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)
}
