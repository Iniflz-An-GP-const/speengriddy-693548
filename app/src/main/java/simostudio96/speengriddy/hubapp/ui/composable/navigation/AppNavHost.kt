package simostudio96.speengriddy.hubapp.ui.composable.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import simostudio96.speengriddy.hubapp.data.model.PuzzleLevel
import simostudio96.speengriddy.hubapp.ui.composable.screen.about.SUEDPAboutScreen
import simostudio96.speengriddy.hubapp.ui.composable.screen.game.SUEDPGameScreen
import simostudio96.speengriddy.hubapp.ui.composable.screen.game_records.SUEDPGameRecordsScreen
import simostudio96.speengriddy.hubapp.ui.composable.screen.main_menu.SUEDPMainMenuScreen
import simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.SUEDPPuzzleSetupScreen
import simostudio96.speengriddy.hubapp.ui.composable.screen.splash.SUEDPSplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val activity = LocalActivity.current

    NavHost(
        navController = navController,
        startDestination = NavRoute.Splash,
        modifier = modifier,
    ) {
        composable<NavRoute.Splash> {
            SUEDPSplashScreen(
                onMainMenuScreenNavigation = {
                    navController.navigate(route = NavRoute.MainMenu) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NavRoute.MainMenu> {
            SUEDPMainMenuScreen(
                onPuzzleSelectionScreenNavigation = { navController.navigate(route = NavRoute.PuzzleSetup) },
                onGameRecordsScreenNavigation = { navController.navigate(route = NavRoute.GameRecords) },
                onAboutScreenNavigation = { navController.navigate(route = NavRoute.About) },
                onExit = { activity?.finishAffinity() }
            )
        }

        composable<NavRoute.PuzzleSetup> {
            SUEDPPuzzleSetupScreen(
                onStartGame = { puzzleId, level ->
                    navController.navigate(
                        route = NavRoute.Game(
                            puzzleId = puzzleId,
                            level = level.name,
                        )
                    )
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable<NavRoute.Game> { backStackEntry ->
            val gameRoute: NavRoute.Game = backStackEntry.toRoute()
            SUEDPGameScreen(
                puzzleId = gameRoute.puzzleId,
                level = PuzzleLevel.valueOf(gameRoute.level),
                onExitToMainMenu = {
                    navController.navigate(route = NavRoute.MainMenu) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NavRoute.About> {
            SUEDPAboutScreen(onBackClick = { navController.popBackStack() })
        }

        composable<NavRoute.GameRecords> {
            SUEDPGameRecordsScreen(onBackClick = { navController.popBackStack() })
        }
    }
}