package simostudio96.speengriddy.hubapp.ui.composable.navigation

import kotlinx.serialization.Serializable

sealed class NavRoute {
    @Serializable
    object Splash : NavRoute()

    @Serializable
    object MainMenu : NavRoute()

    @Serializable
    object About : NavRoute()

    @Serializable
    object PuzzleSetup : NavRoute()

    @Serializable
    data class Game(
        val puzzleId: Int,
        val level: String,
    ) : NavRoute()

    @Serializable
    object GameRecords : NavRoute()
}