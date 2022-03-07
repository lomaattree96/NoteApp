package com.example.tasks.data.repository

import com.example.tasks.data.model.Notes
import kotlinx.coroutines.flow.Flow

// Interface define a form of behaviour that the implementing type have to follow.
//contains declarartions for abstract class and methods for implementing.
//cannot stor e a state
interface Note_repo {
    //a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value.
    // For example, flow is used  to receive live updates from a database.
    fun getNotes(): Flow<List<Notes>>
    suspend fun getNoteByid(id: Int): Notes?
    suspend fun insertNote(note :Notes)
    suspend fun deleteNote(note:Notes)
}
