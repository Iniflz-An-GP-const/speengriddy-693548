package simostudio96.speengriddy.hubapp.ui.composable.screen.splash.cover

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp

internal const val CoverColumns = 6
internal val CoverGap = 3.dp
internal val CoverCornerSize = 10.dp

internal inline fun DrawScope.forEachCoverCell(
    block: (row: Int, column: Int, rows: Int, center: Offset, cellSize: Float) -> Unit,
) {
    val cellSize = size.width / CoverColumns
    val rows = (size.height / cellSize).toInt() + 1

    for (row in 0 until rows) {
        for (column in 0 until CoverColumns) {
            val center = Offset((column + 0.5f) * cellSize, (row + 0.5f) * cellSize)
            block(row, column, rows, center, cellSize)
        }
    }
}
