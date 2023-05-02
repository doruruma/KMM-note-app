package com.andrayuusha.note_app_kmm.android.di

import android.app.Application
import com.andrayuusha.note_app_kmm.data.local.DatabaseDriverFactory
import com.andrayuusha.note_app_kmm.data.note.SqlDelightNoteDataSource
import com.andrayuusha.note_app_kmm.database.NoteDatabase
import com.andrayuusha.note_app_kmm.domain.note.NoteDataSource
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).createDriver()
    }

    @Provides
    @Singleton
    fun provideNoteDataSource(driver: SqlDriver): NoteDataSource {
        return SqlDelightNoteDataSource(NoteDatabase(driver))
    }

}