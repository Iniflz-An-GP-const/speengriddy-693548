package simostudio96.speengriddy.hubapp.ui.composable.app_root.background.decor

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.platform.LocalDensity
import simostudio96.speengriddy.hubapp.ui.composable.app_root.background.BackgroundMotion
import simostudio96.speengriddy.hubapp.ui.composable.app_root.background.BackgroundPoints
import simostudio96.speengriddy.hubapp.ui.composable.app_root.background.drawTiled

private const val TILE_DP = 190f
private const val FILL_ALPHA = 0.07f
private const val STROKE_ALPHA = 0.18f
private const val STROKE_DP = 1.5f

private const val COUNT = 6

private data class Spot(val x: Float, val y: Float, val sizeDp: Float, val rotation: Float)

private val Sizes = listOf(46f, 36f, 54f, 40f, 38f)
private val Rotations = listOf(-12f, 18f, 8f, -20f, 25f, -6f)

@Composable
fun BackgroundDecor(
    motion: BackgroundMotion,
    points: BackgroundPoints,
    modifier: Modifier = Modifier
) {
    val color = MaterialTheme.colorScheme.primary
    val density = LocalDensity.current.density
    val spots = remember(points) {
        points(COUNT).mapIndexed { index, point ->
            Spot(point.x, point.y, Sizes[index % Sizes.size], Rotations[index % Rotations.size])
        }
    }
    val outlines = remember(spots, density) { spots.map { pieceOutline(it.sizeDp * density) } }

    Canvas(modifier = modifier.fillMaxSize()) {
        val alpha = motion.alpha()
        val tile = TILE_DP * density

        drawTiled(tile, motion.shift()) {
            spots.forEachIndexed { index, spot ->
                val side = spot.sizeDp * density
                translate(left = spot.x * tile, top = spot.y * tile) {
                    rotate(degrees = spot.rotation, pivot = Offset(side / 2f, side / 2f)) {
                        drawPath(outlines[index], color.copy(alpha = FILL_ALPHA * alpha))
                        drawPath(
                            path = outlines[index],
                            color = color.copy(alpha = STROKE_ALPHA * alpha),
                            style = Stroke(width = STROKE_DP * density),
                        )
                    }
                }
            }
        }
    }
}

private fun pieceOutline(side: Float): Path {
    val radius = side * 0.2f
    val body = Path().apply { addRect(Rect(0f, 0f, side, side)) }
    val knobTop = circle(Offset(side / 2f, -radius * 0.6f), radius)
    val knobRight = circle(Offset(side + radius * 0.6f, side / 2f), radius)
    val socketLeft = circle(Offset(radius * 0.6f, side / 2f), radius)

    val withKnobs = Path.combine(
        PathOperation.Union,
        Path.combine(PathOperation.Union, body, knobTop),
        knobRight,
    )
    return Path.combine(PathOperation.Difference, withKnobs, socketLeft)
}

private fun circle(center: Offset, radius: Float): Path =
    Path().apply { addOval(Rect(center, radius)) }
