package com.andrayuusha.note_app_kmm.domain.note

import com.andrayuusha.note_app_kmm.domain.time.DateTimeUtil

class SearchNotes {

    fun execute(notes: List<Note>, query: String): List<Note> {
        if (query.isBlank()) {
            return notes
        }
        return notes.filter { note ->
            note.title.trim().lowercase().contains(query.lowercase()) ||
                    note.content.trim().lowercase().contains(query.lowercase())
        }.sortedBy { note ->
            DateTimeUtil.toEpochMillis(note.created)
        }
    }

}