package com.example.tasks.feature_note.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule

import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tasks.MainActivity
import com.example.tasks.core.util.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.example.tasks.di.AppModule
import com.example.tasks.ui.model.add_newnotes.AddEditNoteScreen
import com.example.tasks.ui.theme.TasksTheme
import com.example.tasks.ui.viewmodels.notes_screens.NotesScreen
import com.example.tasks.util.Nav_Screen



//END TO END TESTING



@HiltAndroidTest
@UninstallModules(AppModule:: class)
class Notes_endtoendtest {
    @get: Rule(order = 0)
    val hiltRule = HiltAndroidRule(this) //test instance is this

    //for composable function
    @get:Rule(order = 1)
//specify our own activity that we want to launch
    val composeRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @Before
    fun Setup() {
        hiltRule.inject()
        composeRule.setContent {

            //first end to end testing will be saving new note and adding new note
            TasksTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Nav_Screen.NotesScreen.route
                ) {
                    composable(route = Nav_Screen.NotesScreen.route) {
                        NotesScreen(navController = navController)
                    }
                    composable(
                        route = Nav_Screen.AddEditNoteScreen.route +
                                "?noteId={noteId}&noteColor={noteColor}",
                        arguments = listOf(
                            navArgument(
                                name = "noteId"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            },
                            navArgument(
                                name = "noteColor"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            },
                        )
                    ) {
                        val color = it.arguments?.getInt("noteColor") ?: -1
                        AddEditNoteScreen(
                            navController = navController,
                            noteColor = color
                        )
                    }
                }
            }
        }
    }



    @Test

    fun saveNewNotes_editAfterwards(){

        //click on fab to save new note and get new note screen
        //check our notescreen for add
        composeRule.onNodeWithContentDescription("Add")


        //enter text in title  and content text field
        composeRule
            .onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
            .performTextInput("test-title")

        composeRule
            .onNodeWithTag(TestTags.CONTENT_TEXT_FIELD)
            .performTextInput("test-content")

        //save the note
        //check add edit note screen
        composeRule
            .onNodeWithContentDescription("Save")
            .performClick()
//NODE IS ACTUALLY IN OUR LIST
        //make sure there is note in the list with our title and content text field
        composeRule
            .onNodeWithText("test-title")
            .assertIsDisplayed()
//click on note to edit it
        composeRule
            .onNodeWithText("test-title")
            .performClick()


        //to make sure that addeditnotescreen actually contain title and content text field  ----contain title and content of notes
        composeRule
            .onNodeWithText(TestTags.TITLE_TEXT_FIELD)
            .assertTextEquals("test-title")

        composeRule
            .onNodeWithText(TestTags.CONTENT_TEXT_FIELD)
            .assertTextEquals("test-content")
        //add the text "2" to the title text field

        composeRule
            .onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
            .performTextInput("2")

//to make sure we can find in the list that we have test-title with 2.

            //update the note
        composeRule
            .onNodeWithContentDescription("Save")
            .performClick()

//make sure the updatde was applied to the list
        composeRule
            .onNodeWithText("test-title2")
            .assertIsDisplayed() //first end to end

    }









    @Test
    fun saveNewNotes_orderByTitleDescending() {
        for(i in 1..3) {
            // Click on FAB to get to add note screen
            composeRule.onNodeWithContentDescription("Add").performClick()

            // Enter texts in title and content text fields
            composeRule
                .onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
                .performTextInput(i.toString())
            composeRule
                .onNodeWithTag(TestTags.CONTENT_TEXT_FIELD)
                .performTextInput(i.toString())
            // Save the new
            composeRule.onNodeWithContentDescription("Save").performClick()
        }

        composeRule.onNodeWithText("1").assertIsDisplayed()
        composeRule.onNodeWithText("2").assertIsDisplayed()
        composeRule.onNodeWithText("3").assertIsDisplayed()

        composeRule
            .onNodeWithContentDescription("Sort")
            .performClick()
        composeRule
            .onNodeWithContentDescription("Title")
            .performClick()
        composeRule
            .onNodeWithContentDescription("Descending")
            .performClick()

        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[0]
            .assertTextContains("3")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[1]
            .assertTextContains("2")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[2]
            .assertTextContains("1")
    }
}


