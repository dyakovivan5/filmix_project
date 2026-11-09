package com.example.filmix_project

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import com.example.filmix_project.ui.components.BottomTab
import com.example.filmix_project.ui.theme.Filmix_projectTheme

class MainActivity : ComponentActivity() {

    private val requestedTab = mutableStateOf<BottomTab?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestedTab.value = readTab(intent)
        setContent {
            Filmix_projectTheme {
                AppRoot(
                    requestedTab = requestedTab.value,
                    onTabHandled = { requestedTab.value = null }
                )
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        requestedTab.value = readTab(intent)
    }

    private fun readTab(intent: Intent): BottomTab? {
        val name = intent.getStringExtra(EXTRA_TAB) ?: return null
        intent.removeExtra(EXTRA_TAB)
        return BottomTab.entries.firstOrNull { it.name == name }
    }

    companion object {
        const val EXTRA_TAB = "tab"
    }
}
