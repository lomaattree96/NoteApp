package com.example.tasks.di

import android.app.Application
import androidx.room.Room
import com.example.tasks.data.datasource.NoteDao
import com.example.tasks.data.datasource.Note_database

import com.example.tasks.data.repository.Note_repo
import com.example.tasks.data.repository.Notesrepoimpl

import com.example.tasks.ui.model.add_newnotes.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATA_FILE_STORE_NAME = "user_prefs.pb"



  /**  @Singleton
    @Provides

    fun provideAppDatabase(@ApplicationContext context: Context):Taskdatabase = Room.databaseBuilder(
        context,
        Taskdatabase::class.java,
        "task_tbl").build()


    @Singleton
    @Provides
    fun providesTaskDao(taskdatabase: Taskdatabase): Task_Dao = taskdatabase.TaskDao()

    @Singleton
    @Provides
    fun providesTaskRepository(taskDao: Task_Dao): Task_Repo = Task_Repo_Imp(taskDao)

    @Singleton
    @Provides
    fun providesNoteDatabase(@ApplicationContext context: Context) :Note_database = Room.databaseBuilder(
        context,
         Note_database:: class.java,
        "note_tbl"
    ).build()
    **/
  @Module
  @InstallIn(SingletonComponent::class)
  object AppModule{
      @Provides
    @Singleton

      fun provideNoteDatabase(app: Application): Note_database{
          return Room.databaseBuilder(
              app,
              Note_database::class.java,
              Note_database.database_name
          ).build()
      }
    fun providesNotesdao(noteDatabase: Note_database): NoteDao = noteDatabase.noteDao()

    @Singleton
    @Provides
    fun providesNotesRepository(db: Note_database): Note_repo {
        return Notesrepoimpl(db.noteDao())
    }


    @Singleton
    @Provides
    fun providesNotesUseCases(repository: Note_repo):Notesusecases{
        return Notesusecases(
            getNotes = GetNotes(repository),
            deletenote = Deletenote(repository),
            getnote = getnote(repository),
            addnote = Addnote(repository)


        )
    }




}

