package simostudio96.speengriddy.hubapp.ui.composable.screen.game.result.actions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import simostudio96.speengriddy.hubapp.R

@Composable
fun SolvedActions(
    onPlayAgain: () -> Unit,
    onMainMenu: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
    ) {
        TextButton(onClick = onMainMenu) {
            Text(text = stringResource(R.string.game_solved_main_menu))
        }
        TextButton(onClick = onPlayAgain) {
            Text(text = stringResource(R.string.game_solved_play_again))
        }
    }
}
