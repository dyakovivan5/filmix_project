package com.example.filmix_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.filmix_project.ui.components.BottomTab
import com.example.filmix_project.ui.components.FilmixBottomBar
import com.example.filmix_project.ui.openMain
import com.example.filmix_project.ui.screens.FavoritesScreen
import com.example.filmix_project.ui.theme.Filmix_projectTheme

class FavoritesActivity : ComponentActivity() {

    private var refreshKey by mutableIntStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Filmix_projectTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        FilmixBottomBar(
                            selected = BottomTab.Favorites,
                            onSelect = { tab ->
                                if (tab != BottomTab.Favorites) {
                                    openMain(this@FavoritesActivity, tab)
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    FavoritesScreen(
                        refreshKey = refreshKey,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        refreshKey++
    }
}
