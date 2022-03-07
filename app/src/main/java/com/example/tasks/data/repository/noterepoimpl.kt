package com.example.tasks.data.repository

import com.example.tasks.data.datasource.NoteDao
import com.example.tasks.data.model.Notes
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

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