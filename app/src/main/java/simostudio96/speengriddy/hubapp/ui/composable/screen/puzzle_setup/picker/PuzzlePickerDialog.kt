package simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.picker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import simostudio96.speengriddy.hubapp.R
import simostudio96.speengriddy.hubapp.data.model.Puzzle
import simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.picker.PuzzlePickerTile
import simostudio96.speengriddy.hubapp.ui.theme.LocalDimens

private const val ColumnCount = 3
private val MaxGridHeight = 420.dp

@Composable
fun PuzzlePickerDialog(
    puzzles: List<Puzzle>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    onDismiss: () -> Unit,
) {
    val dimens = LocalDimens.current

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(dimens.cardCornerRadius),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 4.dp,
        ) {
            Column(
                modifier = Modifier.padding(dimens.cardPadding),
                verticalArrangement = Arrangement.spacedBy(dimens.contentSpacing),
            ) {
                Text(
                    text = stringResource(R.string.puzzle_picker_title),
                    style = MaterialTheme.typography.titleLarge,
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(ColumnCount),
                    modifier = Modifier.heightIn(max = MaxGridHeight),
                    verticalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
                    horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
                ) {
                    itemsIndexed(puzzles, key = { _, puzzle -> puzzle.id }) { index, puzzle ->
                        PuzzlePickerTile(
                            puzzle = puzzle,
                            isSelected = index == selectedIndex,
                            onClick = { onSelect(index) },
                        )
                    }
                }
            }
        }
    }
}
