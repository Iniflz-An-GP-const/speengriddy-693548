package simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.layout

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.layout.ControlsBlock
import simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.layout.SetupLayoutScaffold

@Composable
fun PuzzleSetupLayout(
    topBar: @Composable () -> Unit,
    pager: @Composable (Modifier) -> Unit,
    puzzleTitle: @Composable () -> Unit,
    indicator: @Composable () -> Unit,
    levelSelector: @Composable () -> Unit,
    bestTime: @Composable () -> Unit,
    startButton: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    SetupLayoutScaffold(topBar = topBar, modifier = modifier) {
        puzzleTitle()
        pager(Modifier
            .fillMaxWidth()
            .weight(1f))
        indicator()
        ControlsBlock {
            levelSelector()
            bestTime()
        }
        ControlsBlock { startButton() }
    }
}
