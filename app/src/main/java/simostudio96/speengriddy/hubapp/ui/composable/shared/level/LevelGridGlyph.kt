package simostudio96.speengriddy.hubapp.ui.composable.shared.level

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

private const val GapRatio = 0.12f
private const val EmptySlotAlpha = 0.3f

@Composable
fun LevelGridGlyph(
    gridSize: Int,
    color: Color,
    size: Dp,
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier.size(size)) {
        val gap = this.size.minDimension * GapRatio / gridSize
        val cell = (this.size.minDimension - gap * (gridSize - 1)) / gridSize
        val corner = CornerRadius(cell * 0.25f)

        for (row in 0 until gridSize) {
            for (column in 0 until gridSize) {
                val isEmptySlot = row == gridSize - 1 && column == gridSize - 1
                drawRoundRect(
                    color = if (isEmptySlot) color.copy(alpha = EmptySlotAlpha) else color,
                    topLeft = Offset(column * (cell + gap), row * (cell + gap)),
                    size = Size(cell, cell),
                    cornerRadius = corner,
                )
            }
        }
    }
}
