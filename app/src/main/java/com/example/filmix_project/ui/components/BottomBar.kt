package com.example.filmix_project.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.filmix_project.R

enum class BottomTab {
    Home,
    Favorites,
    Settings
}

@Composable
fun FilmixBottomBar(
    selected: BottomTab,
    onSelect: (BottomTab) -> Unit
) {
    Column {
        HorizontalDivider()
        NavigationBar {
            for (tab in BottomTab.entries) {
                NavigationBarItem(
                    selected = tab == selected,
                    onClick = { onSelect(tab) },
                    icon = { Icon(imageVector = tabIcon(tab, tab == selected), contentDescription = null) },
                    label = {
                        Text(
                            text = stringResource(tabLabel(tab)),
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    alwaysShowLabel = true,
                )
            }
        }
    }
}

private fun tabIcon(tab: BottomTab, selected: Boolean): ImageVector {
    return when (tab) {
        BottomTab.Home -> Icons.Outlined.Home
        BottomTab.Favorites -> if (selected) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder
        BottomTab.Settings -> Icons.Outlined.Settings
    }
}

private fun tabLabel(tab: BottomTab): Int {
    return when (tab) {
        BottomTab.Home -> R.string.nav_home
        BottomTab.Favorites -> R.string.nav_favorites
        BottomTab.Settings -> R.string.nav_settings
    }
}
