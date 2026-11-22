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
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.filmix_project.R
import com.example.filmix_project.ui.theme.Accent
import com.example.filmix_project.ui.theme.CardBackground
import com.example.filmix_project.ui.theme.DividerColor
import com.example.filmix_project.ui.theme.TextSecondary

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
        HorizontalDivider(color = DividerColor)
        NavigationBar(
            containerColor = CardBackground,
            tonalElevation = 0.dp
        ) {
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
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Accent,
                        selectedTextColor = Accent,
                        unselectedIconColor = TextSecondary,
                        unselectedTextColor = TextSecondary,
                        indicatorColor = Color.Transparent
                    )
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
