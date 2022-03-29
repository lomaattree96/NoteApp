package com.example.tasks.featurenote.domain.domain_usecase

import com.example.tasks.data.model.Notes
import com.example.tasks.featurenote.domain.data.repository.fakenote_repository
import com.example.tasks.ui.model.add_newnotes.use_case.GetNotes
import com.example.tasks.util.Note_order
import com.example.tasks.util.Ordertype
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

 //UI TESTING

//that's why we make it as an interface
class GetNotesTest{
    //use case and repository needed
    private lateinit var getNotes: GetNotes
    private lateinit var fakeRepository: fakenote_repository

    //it will tell junit that it is our before function that will called setup function
    //before every single test case and is used to set things that is used for every test case
    //such as just initialising some objects like our get notes use case

    @Before
    fun Setup(){
        fakeRepository = fakenote_repository()
        //pass a repository
        //test cases should be quick . shouldnot rely on any data writing logic
        //create fake repository(stimulates the actual behaviour of our repository) --- only needs to know which data it gets
        getNotes = GetNotes( fakeRepository )
        //to test ordering of our notes
        //prepopulate our fake database with some notes
        val notestoInsert = mutableListOf<Notes>()
        //we can save for our each indexed and our character
        ('a'..'z').forEachIndexed{index , c->
            //insert 26 notes here for each single character
            notestoInsert.add(
                Notes(
                    //to check something whether it is in correct order or not
                   title =  c.toString(),
                    content = c.toString(),
                    timeStamp = index.toLong(),
                    color = index

                )
            )

        }
        //to check for random order
        notestoInsert.shuffle()

        //suspend function will come so we need to execute that and run blocking
        runBlocking {
            notestoInsert.forEach { fakeRepository.insertNote(it) }
        }


    }
    //running our testcases --orderlist by title ascending
    @Test
    fun `order  notes  by title ascending  ,correct order` () = runBlocking{
        //we need to call for our use case which we need to check for

        val notes = getNotes(Note_order.Title(Ordertype.Ascending)).first()

        //need to test whether  order is really by title and ascending
        for (i in 0..notes.size-2){
            //if its succeds it succeds else if its fails it fails --assertion function
            assertThat(notes[i].title).isLessThan(notes[i+1].title)



        }

    }
    @Test
    fun `order  notes  by title descending  correct order` () = runBlocking{
        //we need to call for our use case which we need to check for

        val notes = getNotes(Note_order.Title(Ordertype.Descending)).first()

        //need to test whether  order is really by title and ascending
        for (i in 0..notes.size-2){
            //if its succeds it succeds else if its fails it fails --assertion function
            assertThat(notes[i].title).isGreaterThan(notes[i+1].title)



        }

    }
    @Test
    fun `order  notes  by date ascending  correct order` () = runBlocking{
        //we need to call for our use case which we need to check for

        val notes = getNotes(Note_order.Date(Ordertype.Ascending)).first()

        //need to test whether  order is really by title and ascending
        for (i in 0..notes.size - 2){
            //if its succeds it succeds else if its fails it fails --assertion function
            assertThat(notes[i].timeStamp).isLessThan(notes[i+1].timeStamp)



        }

    }
    @Test
    fun `Order notes by date descending, correct order`() = runBlocking {
        //we need to call for our use case which we need to check for
        val notes = getNotes(Note_order.Date(Ordertype.Descending)).first()
        //need to test whether  order is really by title and ascending
        for(i in 0..notes.size - 2) {
            //if its succeds it succeds else if its fails it fails --assertion function
            assertThat(notes[i].timeStamp).isGreaterThan(notes[i+1].timeStamp)
        }
    }

    @Test
    fun `order  notes  by color ascending  correct order` () = runBlocking{
        //we need to call for our use case which we need to check for

        val notes = getNotes(Note_order.Color(Ordertype.Ascending)).first()

        //need to test whether  order is really by title and ascending
        for (i in 0..notes.size-2){
            //if its succeds it succeds else if its fails it fails --assertion function
            assertThat(notes[i].color).isLessThan(notes[i+1].color)



        }

    }
    @Test
    fun `order  notes  by color descending  correct order` () = runBlocking{
        //we need to call for our use case which we need to check for

        val notes = getNotes(Note_order.Color(Ordertype.Descending)).first()

        //need to test whether  order is really by title and ascending
        for (i in 0..notes.size-2){
            //if its succeds it succeds else if its fails it fails --assertion function
            assertThat(notes[i].color).isGreaterThan(notes[i+1].color)



        }

    }


}