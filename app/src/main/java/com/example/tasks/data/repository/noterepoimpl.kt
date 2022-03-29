package com.example.tasks.data.repository

import com.example.tasks.data.datasource.NoteDao
import com.example.tasks.data.model.Notes
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//to tell Hilt how to provide instances of AppNavigatorImpl.
// Since this class can be constructor injected, we can just annotate its constructor with @Inject.
class Notesrepoimpl @Inject constructor( private val NotesDao: NoteDao) :Note_repo{
    override fun getNotes(): Flow<List<Notes>> {
      return  NotesDao.getNotes()
    }

    override suspend fun getNoteByid(id: Int): Notes? {
       return NotesDao.getNotebyid(id)
    }

    override suspend fun insertNote(note: Notes) {
       NotesDao.insertNote(note)
    }

    override suspend fun deleteNote(note: Notes) {
        NotesDao.deleteNote(note)
    }

}