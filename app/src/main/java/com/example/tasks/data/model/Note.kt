package com.example.tasks.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tasks.ui.theme.Light_Blue
import com.example.tasks.ui.theme.Red_Orange
import com.example.tasks.ui.theme.Red_Pink
import com.example.tasks.ui.theme.Violet
//entity : class will have a mapping SQLite table in the database.
@Entity(tableName= "note_tbl")
data class Notes(

    val title : String,
    val content:String,
    val timeStamp: Long,
    val color :Int,
    @PrimaryKey val id: Int? = null
//Each entity must have at least 1 field annotated with PrimaryKey
){
    //companion object is used to access the creation of database using singelton pattern so that
    //we can create only one instance of room database at the same time
    companion object{
        val note_colors = listOf(Red_Orange, Light_Blue, Violet, Red_Pink,)
    }
}

class InvalidNoteException(message: String): Exception(message)
//to throw error message if title or content in the note is empty