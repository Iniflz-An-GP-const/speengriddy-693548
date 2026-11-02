package simostudio96.speengriddy.hubapp.ui.composable.app_root.topbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import simostudio96.speengriddy.hubapp.ui.composable.app_root.topbar.AppTopBarBackButton
import simostudio96.speengriddy.hubapp.ui.composable.app_root.topbar.AppTopBarTitle
import simostudio96.speengriddy.hubapp.ui.theme.StatusBarIconsFor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    onNavigateBack: (() -> Unit)? = null,
    subtitle: String? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    val containerColor = MaterialTheme.colorScheme.primaryContainer

    StatusBarIconsFor(containerColor)

    TopAppBar(
        title = { AppTopBarTitle(title = title, subtitle = subtitle) },
        navigationIcon = {
            if (onNavigateBack != null) {
                AppTopBarBackButton(onNavigateBack)
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
    )
}
