package simostudio96.speengriddy.hubapp.ui.composable.app_root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import simostudio96.speengriddy.hubapp.ui.composable.app_root.background.AppBackground
import simostudio96.speengriddy.hubapp.ui.composable.navigation.AppNavHost
import simostudio96.speengriddy.hubapp.ui.composable.navigation.NavRoute
import simostudio96.speengriddy.hubapp.ui.theme.SystemBarsVisibility
import kotlin.reflect.KClass

private val backgroundHiddenScreens: List<KClass<out NavRoute>> = listOf(
    NavRoute.Splash::class,
    NavRoute.Game::class,
)

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val shouldShowBackground = !backStackEntry?.destination.matchesAnyRoute(backgroundHiddenScreens)

    SystemBarsVisibility(visible = false)

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            if (shouldShowBackground) {
                AppBackground(modifier = Modifier.fillMaxSize())
            }

            AppNavHost(
                navController = navController,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            )
        }
    }
}

private fun NavDestination?.matchesAnyRoute(routes: List<KClass<out NavRoute>>): Boolean =
    this != null && routes.any { route -> hasRoute(route) }
