package com.example.tasks.ui.model.add_newnotes.use_case

import com.example.tasks.data.model.Notes
import com.example.tasks.data.repository.Note_repo
import com.example.tasks.util.Note_order
import com.example.tasks.util.Ordertype
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(private val repository: Note_repo){
    operator fun invoke(noteOrder: Note_order =Note_order.Date(Ordertype.Descending)):
            Flow<List<Notes>>{
        return repository.getNotes().map{
            notes ->
            when(noteOrder.ordertype){
                is Ordertype.Ascending ->{
                    when(noteOrder){
                        is Note_order.Title -> notes.sortedBy { it.title.lowercase() }
                        is Note_order.Date -> notes.sortedBy { it.timeStamp }
                        is Note_order.Color -> notes.sortedBy { it.color }
                    }
                }
                is Ordertype.Descending ->{
                    when(noteOrder){
                        is Note_order.Title -> notes.sortedBy { it.title.lowercase() }
                        is Note_order.Date -> notes.sortedBy { it.timeStamp }
                        is Note_order.Color -> notes.sortedBy { it.color }
                    }
                }
            }
        }
    }
}