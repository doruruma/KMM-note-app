package com.andrayuusha.note_app_kmm.data.note

import com.andrayuusha.note_app_kmm.domain.note.Note
import com.andrayuusha.note_app_kmm.domain.time.DateTimeUtil
import database.NoteEntity

fun NoteEntity.toNote(): Note {
    return Note(
        id,
        title,
        content,
        colorHex,
        DateTimeUtil.fromEpochMillis(created)
    )
}