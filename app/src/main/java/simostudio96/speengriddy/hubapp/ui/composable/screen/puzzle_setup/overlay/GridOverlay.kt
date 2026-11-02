package simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.overlay

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import simostudio96.speengriddy.hubapp.ui.theme.LocalMotion

private const val LineAlpha = 0.85f
private val LineWidth = 1.5.dp

@Composable
fun GridOverlay(
    gridSize: Int,
    modifier: Modifier = Modifier,
) {
    val motion = LocalMotion.current

    Crossfade(
        targetState = gridSize,
        modifier = modifier,
        animationSpec = tween(durationMillis = motion.crossfadeMillis),
        label = "gridOverlayLines",
    ) { cells ->
        Canvas(modifier = Modifier.fillMaxSize()) {
            val color = Color.White.copy(alpha = LineAlpha)
            val strokeWidth = LineWidth.toPx()

            for (index in 1 until cells) {
                val x = size.width * index / cells
                val y = size.height * index / cells
                drawLine(color, Offset(x, 0f), Offset(x, size.height), strokeWidth)
                drawLine(color, Offset(0f, y), Offset(size.width, y), strokeWidth)
            }
        }
    }
}
