package com.example.eventsapp.di

import android.app.Application
import androidx.room.Room
import com.example.eventsapp.data.EventDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

// more efficient fo dagger is object
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    // provider - data base object
    @Provides
    @Singleton
    fun provideDatabase(
        app: Application,
        callback: EventDatabase.Callback
    ) = Room.databaseBuilder(app, EventDatabase::class.java, "event_database")
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    // provider - dao
    @Provides
    fun provideEventDao(db: EventDatabase) = db.eventDao()

    // provide coroutine scope that lives as long as our app lives
    // supervisor job keeps other childs running if one fails! thats good
    @ApplicationScope
    @Provides
    @Singleton
    fun provideAppllicationScope() = CoroutineScope(SupervisorJob())
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope
