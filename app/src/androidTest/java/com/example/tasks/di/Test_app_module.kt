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

//UI TESTING



//The DatabaseModule module is installed in the SingletonComponent,
// so the bindings are available in the application container.
// Our new navigation information (i.e. AppNavigator) needs information specific to the activity becauseAppNavigatorImpl has an Activity as a dependency.
// Therefore, it must be installed in the Activity container instead of the Application container,
// since that's where information about the Activity is available.

@Module
  @InstallIn(SingletonComponent::class)
  object Test_app_module{
      @Provides
    @Singleton
//An entry point is an interface with an accessor method for each binding type we want (including its qualifier).
// Also, the interface must be annotated with @InstallIn to specify the component in which to install the entry point.
      fun provideNoteDatabase(app: Application): Note_database{
          //for test case
          return Room.inMemoryDatabaseBuilder(
              //rooms gives a so called in memory data storage
              app,
              Note_database::class.java,
            //  Note_database.database_name
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

