package simostudio96.speengriddy.hubapp.ui.composable.screen.game

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import simostudio96.speengriddy.hubapp.ui.composable.screen.game.swap.pieceMotion
import simostudio96.speengriddy.hubapp.ui.theme.LocalDimens
import simostudio96.speengriddy.hubapp.ui.viewmodel.GameUiState
import kotlin.math.roundToInt

@Composable
internal fun PuzzleBoard(
    uiState: GameUiState,
    onCellClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val columns = uiState.cols
    if (columns <= 0) return

    val dimens = LocalDimens.current
    val density = LocalDensity.current
    val selectionColor = MaterialTheme.colorScheme.primary

    val rows = uiState.pieces.size / columns
    val boardAspect = columns * uiState.pieceAspect / rows

    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        val boardWidth = minOf(maxWidth, maxHeight * boardAspect)
        val boardHeight = boardWidth / boardAspect
        val boardWidthPx = with(density) { boardWidth.toPx() }
        val boardHeightPx = with(density) { boardHeight.toPx() }

        // The edges of the cells are whole pixels, and every cell ends exactly where the next one
        // begins. With fractional sizes a hairline of the background would show between two
        // pieces that are joined.
        fun boundsOf(cellIndex: Int): CellBounds {
            val left = ((cellIndex % columns) * boardWidthPx / columns).roundToInt()
            val right = (((cellIndex % columns) + 1) * boardWidthPx / columns).roundToInt()
            val top = ((cellIndex / columns) * boardHeightPx / rows).roundToInt()
            val bottom = (((cellIndex / columns) + 1) * boardHeightPx / rows).roundToInt()
            return CellBounds(
                offset = IntOffset(left, top),
                width = right - left,
                height = bottom - top
            )
        }

        Box(modifier = Modifier.size(width = boardWidth, height = boardHeight)) {
            uiState.pieces.indices.forEach { pieceIndex ->
                key(pieceIndex) {
                    val cellIndex = uiState.order.indexOf(pieceIndex)
                    val bounds = boundsOf(cellIndex)

                    PuzzleCell(
                        piece = uiState.pieces[pieceIndex],
                        joined = joinedSidesOf(uiState.order, cellIndex, columns, rows),
                        enabled = !uiState.isSolved,
                        onClick = { onCellClick(cellIndex) },
                        modifier = Modifier
                            .size(
                                width = with(density) { bounds.width.toDp() },
                                height = with(density) { bounds.height.toDp() },
                            )
                            .pieceMotion(bounds.offset),
                    )
                }
            }

            uiState.selectedCell?.let { selectedCell ->
                val bounds = boundsOf(selectedCell)
                val joined = joinedSidesOf(uiState.order, selectedCell, columns, rows)
                val gap = dimens.pieceGap

                Box(
                    modifier = Modifier
                        .offset { bounds.offset }
                        .size(
                            width = with(density) { bounds.width.toDp() },
                            height = with(density) { bounds.height.toDp() },
                        )
                        .padding(
                            PaddingValues.Absolute(
                                left = if (joined.start) 0.dp else gap,
                                top = if (joined.top) 0.dp else gap,
                                right = if (joined.end) 0.dp else gap,
                                bottom = if (joined.bottom) 0.dp else gap,
                            ),
                        )
                        .drawBehind {
                            // The stroke is centred on a rectangle grown by half of its width, so
                            // all of it lies outside the piece.
                            val stroke = dimens.pieceSelectedBorderWidth.toPx()
                            drawRect(
                                color = selectionColor,
                                topLeft = Offset(-stroke / 2f, -stroke / 2f),
                                size = Size(size.width + stroke, size.height + stroke),
                                style = Stroke(width = stroke),
                            )
                        },
                )
            }
        }
    }
}

private class CellBounds(
    val offset: IntOffset,
    val width: Int,
    val height: Int,
)

/**
 * The sides of the piece in [cellIndex] that touch the piece which really belongs there:
 * piece p has p - 1 to the left, p + 1 to the right and p -/+ columns above and below.
 */
private fun joinedSidesOf(order: List<Int>, cellIndex: Int, columns: Int, rows: Int): JoinedSides {
    val column = cellIndex % columns
    val row = cellIndex / columns
    val piece = order[cellIndex]

    val start = column > 0 && piece % columns != 0 && order[cellIndex - 1] == piece - 1
    val end =
        column < columns - 1 && piece % columns != columns - 1 && order[cellIndex + 1] == piece + 1
    val top = row > 0 && order[cellIndex - columns] == piece - columns
    val bottom = row < rows - 1 && order[cellIndex + columns] == piece + columns

    // Two joined sides make a corner; it is a full block only if the piece diagonally across the
    // corner is the right one too. Otherwise the corner is notched.
    return JoinedSides(
        start = start,
        top = top,
        end = end,
        bottom = bottom,
        notchTopStart = start && top && order[cellIndex - columns - 1] != piece - columns - 1,
        notchTopEnd = top && end && order[cellIndex - columns + 1] != piece - columns + 1,
        notchBottomEnd = end && bottom && order[cellIndex + columns + 1] != piece + columns + 1,
        notchBottomStart = bottom && start && order[cellIndex + columns - 1] != piece + columns - 1,
    )
}
