package com.example.tasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tasks.ui.model.add_newnotes.AddEditNoteScreen
import com.example.tasks.ui.theme.TasksTheme
import com.example.tasks.ui.viewmodels.notes_screens.NotesScreen
import com.example.tasks.util.Nav_Screen
import dagger.hilt.android.AndroidEntryPoint

//@EntryPoint annotation which is used to inject dependencies in classes not supported by Hilt.
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TasksTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {


                    NoteAppTheme()

                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
private fun NoteAppTheme() {
    Surface(
        color = MaterialTheme.colors.background
    ) {
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
