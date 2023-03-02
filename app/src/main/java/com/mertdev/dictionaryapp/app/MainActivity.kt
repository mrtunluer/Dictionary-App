package com.mertdev.dictionaryapp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mertdev.dictionaryapp.app.ui.theme.DictionaryAppTheme
import com.mertdev.dictionaryapp.utils.bottom_bar.BottomBar
import com.mertdev.dictionaryapp.utils.bottom_bar.BottomNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryAppTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(scaffoldState = scaffoldState,
                    bottomBar = { BottomBar(navController = navController) },
                    content = { paddingValues ->
                        BottomNavGraph(
                            modifier = Modifier.padding(paddingValues),
                            navController = navController
                        )
                    })
            }
        }
    }
}
