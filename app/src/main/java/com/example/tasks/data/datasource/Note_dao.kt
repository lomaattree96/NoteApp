package com.example.tasks.data.datasource

import androidx.room.*
import com.example.tasks.data.model.Notes
import kotlinx.coroutines.flow.Flow
// dao : main classes where you define database interactions , which should be only either interface or abstract class

//includes various queries
@Dao
interface NoteDao {


    @Query("SELECT * FROM note_tbl")
    fun getNotes() :Flow<List<Notes>>

    @Query("SELECT * FROM note_tbl WHERE id = :id")
     fun getNotebyid(id:Int):Notes?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Notes)

    @Delete
    suspend fun deleteNote(note:Notes)


}