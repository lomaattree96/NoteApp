package com.example.tasks.feature_note.note

//INTEGRATION UI TEST --testapp module and hilt test runner


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tasks.MainActivity
import com.example.tasks.core.util.TestTags
import com.example.tasks.di.AppModule
import com.example.tasks.ui.viewmodels.notes_screens.NotesScreen
import com.example.tasks.util.Nav_Screen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

//dagger dont use this module

//looks in all of our directory
// our actual test case
//should be able to inject dependancies with dagger hilt
@HiltAndroidTest
@UninstallModules(AppModule::class)

class Notes__ScreenTest{
    //here different junit rules needs to be added as hiltandroidtest is not enough
    //like activity stuff is used to send stuff like sending intents receiving
//to define our inject dependencies rule first then composable rule we need to put  order
    @get: Rule(order = 0)
    val hiltRule = HiltAndroidRule(this) //test instance is this
    //to stimulate view, clicks

    //for composable function
    @get:Rule(order = 1)
//specify our own activity that we want to launch
    val composeRule = createAndroidComposeRule<MainActivity>()

    //setup function in test case
    @ExperimentalAnimationApi
    @Before
    fun setup() {
        //need to inject dependancies
        hiltRule.inject()
        //to setup our screen
        //UI TESTING
        composeRule.setContent {
            //want to wrap around the navhost.. that way hilt will automatically inject our viewmodels as bind to our nav graph

            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Nav_Screen.NotesScreen.route
            ) {
                composable(route = Nav_Screen.NotesScreen.route) {
                    NotesScreen(navController = navController)
                }
            }
        }
    }


    @Test
    fun ClickOrderToggleSectiion_isVisible() {
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION).assertDoesNotExist()
        //make the order sectoin visible
        composeRule.onNodeWithContentDescription("Sort").performClick()
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION).assertIsDisplayed()
    }
}