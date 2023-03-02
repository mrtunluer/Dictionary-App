package com.mertdev.dictionaryapp.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String, val title: String, val icon: ImageVector
) {
    object WordDataScreen : Screen(
        route = "word_data_screen", title = "Home", icon = Icons.Rounded.Home
    )

    object SavedWordsScreen : Screen(
        route = "saved_words_screen", title = "Saved", icon = Icons.Rounded.Bookmarks
    )
}