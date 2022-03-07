package com.example.tasks.ui.model.add_newnotes.use_case

import com.example.tasks.data.model.Notes
import com.example.tasks.data.repository.Note_repo

class getnote(private val repository: Note_repo){
    suspend operator fun invoke(id:Int) :Notes?{
        return repository.getNoteByid(id)
    }
}