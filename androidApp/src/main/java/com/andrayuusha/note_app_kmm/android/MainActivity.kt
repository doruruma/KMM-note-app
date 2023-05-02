package com.andrayuusha.note_app_kmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.andrayuusha.note_app_kmm.android.note_detail.NoteDetailScreen
import com.andrayuusha.note_app_kmm.android.note_list.NoteListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val rootRoute = "notes_list"
    private val noteDetailArgument = navArgument(name = "noteId") {
        type = NavType.LongType
        defaultValue = -1L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = rootRoute) {
                    // note list screen
                    composable(route = rootRoute) {
                        NoteListScreen(navController = navController)
                    }
                    // note detail screen
                    composable(
                        route = "note_detail/{noteId}",
                        arguments = listOf(noteDetailArgument)
                    ) { backStackEntry ->
                        val noteId = backStackEntry.arguments?.getLong("noteId") ?: -1L
                        NoteDetailScreen(noteId = noteId, navController = navController)
                    }
                }
            }
        }
    }

}
