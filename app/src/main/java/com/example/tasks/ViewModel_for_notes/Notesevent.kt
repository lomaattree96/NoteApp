package com.example.tasks.ui.viewmodels.notes_screens

import com.example.tasks.data.model.Notes
import com.example.tasks.util.Note_order

sealed class NotesEvent {
    data class Order(val noteOrder: Note_order): NotesEvent()
    data class DeleteNote(val note: Notes): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}