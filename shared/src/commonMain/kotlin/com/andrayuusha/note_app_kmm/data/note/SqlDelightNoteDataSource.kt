package com.andrayuusha.note_app_kmm.data.note

import com.andrayuusha.note_app_kmm.database.NoteDatabase
import com.andrayuusha.note_app_kmm.domain.note.Note
import com.andrayuusha.note_app_kmm.domain.note.NoteDataSource
import com.andrayuusha.note_app_kmm.domain.time.DateTimeUtil

class SqlDelightNoteDataSource(db: NoteDatabase) : NoteDataSource {

    private val queries = db.noteQueries

    override suspend fun insertNote(note: Note) {
        queries.insertNote(
            note.id,
            note.title,
            note.content,
            note.colorHex,
            DateTimeUtil.toEpochMillis(note.created)
        )
    }

    override suspend fun getNoteById(id: Long): Note? {
        return queries.getNoteById(id)
            .executeAsOneOrNull()
            ?.toNote()
    }

    override suspend fun getAllNotes(): List<Note> {
        return queries.getAllNotes()
            .executeAsList()
            .map { noteEntity -> noteEntity.toNote() }
    }

    override suspend fun deleteNoteById(id: Long) {
        queries.deleteNoteById(id)
    }

}