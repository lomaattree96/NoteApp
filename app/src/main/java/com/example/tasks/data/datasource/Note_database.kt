package com.example.tasks.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tasks.data.model.Notes
import dagger.Provides
import javax.inject.Singleton

//RoomDatabase provides direct access to the underlying database implementation but you should prefer using Dao classes.
@Singleton
@Database(entities= [Notes:: class] ,version =1)
//create abstract classes to provide a common template for other classes to extend and use.
abstract class Note_database: RoomDatabase(){
    abstract fun  noteDao() :NoteDao
    //to write a function or any member of the class that can be called without having the instance of the class
    // then you can write the same as a member of a companion object inside the class.
    // So, by declaring the companion object,you can access the members of the class by class name only
    // (which is without explicitly creating the instance of the class).
    companion object{
        const val  database_name = "notes_db"
    }

}
