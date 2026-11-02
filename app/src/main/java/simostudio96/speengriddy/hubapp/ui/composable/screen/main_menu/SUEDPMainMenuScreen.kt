package simostudio96.speengriddy.hubapp.ui.composable.screen.main_menu

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import simostudio96.speengriddy.hubapp.ui.composable.screen.main_menu.content.MainMenuContent

@Composable
fun SUEDPMainMenuScreen(
    onPuzzleSelectionScreenNavigation: () -> Unit,
    onGameRecordsScreenNavigation: () -> Unit,
    onAboutScreenNavigation: () -> Unit,
    onExit: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MainMenuContent(
        modifier = modifier,
        onPuzzleSelectionScreenNavigation = onPuzzleSelectionScreenNavigation,
        onGameRecordsScreenNavigation = onGameRecordsScreenNavigation,
        onAboutScreenNavigation = onAboutScreenNavigation,
        onExit = onExit,
    )
}
