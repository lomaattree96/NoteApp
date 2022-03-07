package com.example.tasks.ui.model.add_newnotes.use_case

import com.example.tasks.data.model.InvalidNoteException
import com.example.tasks.data.model.Notes
import com.example.tasks.data.repository.Note_repo

class Addnote
    (private val repository: Note_repo
    )
{
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(notes: Notes){
        if(notes.title.isBlank()){
            throw InvalidNoteException("the title of note_app cannot be emptied")
        }
        if(notes.content.isBlank()){
            throw InvalidNoteException("the content of app is not empty")

        }
        repository.insertNote(notes)
    }
}