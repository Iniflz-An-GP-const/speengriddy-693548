package simostudio96.speengriddy.hubapp.ui.composable.screen.game.result

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import simostudio96.speengriddy.hubapp.ui.composable.screen.game.result.actions.SolvedActions
import simostudio96.speengriddy.hubapp.ui.composable.screen.game.result.appear.solvedAppear
import simostudio96.speengriddy.hubapp.ui.composable.screen.game.result.content.SolvedContent
import simostudio96.speengriddy.hubapp.ui.composable.screen.game.result.shell.SolvedShell
import simostudio96.speengriddy.hubapp.ui.theme.LocalDimens

private val DialogMaxWidth = 420.dp

@Composable
fun SolvedResultDialog(
    info: SolvedInfo,
    onPlayAgain: () -> Unit,
    onMainMenu: () -> Unit,
) {
    val dimens = LocalDimens.current

    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false,
        ),
    ) {
        SolvedShell(
            info = info,
            content = { SolvedContent(info = it) },
            actions = { SolvedActions(onPlayAgain = onPlayAgain, onMainMenu = onMainMenu) },
            modifier = Modifier
                .padding(horizontal = dimens.screenPadding)
                .widthIn(max = DialogMaxWidth)
                .fillMaxWidth()
                .solvedAppear(),
        )
    }
}
