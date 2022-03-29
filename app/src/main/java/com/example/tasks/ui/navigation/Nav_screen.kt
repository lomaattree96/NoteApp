package com.example.tasks.util


//sealed class for navigation
sealed class Nav_Screen(val route: String) {
    object NotesScreen: Nav_Screen("notes_screen")
    object AddEditNoteScreen: Nav_Screen("add_edit_note_screen")
}