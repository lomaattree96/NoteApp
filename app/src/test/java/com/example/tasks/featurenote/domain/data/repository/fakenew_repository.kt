package com.example.tasks.featurenote.domain.data.repository

import com.example.tasks.data.model.Notes
import com.example.tasks.data.repository.Note_repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


//UI TESTING

//our repository is actually an interface

//creates our repository that stimulates our database


class fakenote_repository :Note_repo {

 //stimulates our actual database with just a simple list
    //get the same data for our single repository as our

    private val notes = mutableListOf<Notes>()
    override fun getNotes(): Flow<List<Notes>> {
        //to stimulate our repository we can return our notes
        return flow{ emit(notes)}
    }

    override suspend fun getNoteByid(id: Int): Notes? {
      return notes.find { it.id == id }
    }

    override suspend fun insertNote(note: Notes) {
        notes.add(note)
    }

    override suspend fun deleteNote(note: Notes) {
        notes.remove(note)
    }

}