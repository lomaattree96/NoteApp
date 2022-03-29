package com.example.tasks.ui.viewmodels.notes_screens

import com.example.tasks.data.model.Notes
import com.example.tasks.util.Note_order
import com.example.tasks.util.Ordertype

data class NotesState(
    val notes: List<Notes> = emptyList(),  //notes screen and notesviewmodel
    val noteOrder: Note_order = Note_order.Date(Ordertype.Descending), //notes screen and notesviewmodel
    val isOrderSectionVisible: Boolean = false  //notes screen and notesviewmodel
)