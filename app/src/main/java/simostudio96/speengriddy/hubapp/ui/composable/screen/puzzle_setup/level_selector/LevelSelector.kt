package simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.level_selector

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import simostudio96.speengriddy.hubapp.data.model.PuzzleLevel
import simostudio96.speengriddy.hubapp.ui.composable.shared.level.colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LevelSelector(
    levels: List<PuzzleLevel>,
    selected: PuzzleLevel,
    onSelect: (PuzzleLevel) -> Unit,
    modifier: Modifier = Modifier,
) {
    SingleChoiceSegmentedButtonRow(modifier = modifier.fillMaxWidth()) {
        levels.forEachIndexed { index, level ->
            val colors = level.colors()

            SegmentedButton(
                selected = level == selected,
                onClick = { onSelect(level) },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = levels.size),
                icon = {},
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = colors.container,
                    activeContentColor = colors.onContainer,
                    activeBorderColor = colors.accent,
                ),
                label = {
                    Text(
                        text = stringResource(level.titleRes),
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
            )
        }
    }
}
