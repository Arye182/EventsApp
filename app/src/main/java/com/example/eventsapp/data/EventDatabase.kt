package com.example.eventsapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.eventsapp.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [EventEntity::class], version = 1)
abstract class EventDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao

    class Callback @Inject constructor(
        private val database: Provider<EventDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            // create events as default!
            val dao = database.get().eventDao()
            applicationScope.launch(Dispatchers.IO) {
                dao.save(EventEntity("event number one"))
                dao.save(EventEntity("another event!"))
            }
        }
    }
}
