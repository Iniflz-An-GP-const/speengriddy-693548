package simostudio96.speengriddy.hubapp.ui.composable.screen.splash.cover

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import simostudio96.speengriddy.hubapp.ui.composable.screen.splash.cover.CoverCornerSize
import simostudio96.speengriddy.hubapp.ui.composable.screen.splash.cover.CoverGap
import simostudio96.speengriddy.hubapp.ui.composable.screen.splash.cover.forEachCoverCell
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.hypot
import kotlin.time.Duration.Companion.milliseconds

private const val OPEN_DURATION_MS = 1100
private const val REVEALED_AT = 0.45f
private const val SPREAD = 0.6f

@Composable
fun SplashCover(
    onRevealed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.colorScheme
    val palette = listOf(
        colors.primaryContainer,
        colors.secondaryContainer,
        colors.tertiaryContainer,
        colors.primary,
        colors.secondary,
        colors.tertiary,
    )
    val open = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        coroutineScope {
            launch {
                delay((OPEN_DURATION_MS * REVEALED_AT).toLong().milliseconds)
                onRevealed()
            }
            open.animateTo(
                1f,
                tween(durationMillis = OPEN_DURATION_MS, easing = FastOutSlowInEasing)
            )
        }
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        val gap = CoverGap.toPx()
        val corner = CornerRadius(CoverCornerSize.toPx())
        val screenCenter = Offset(size.width / 2f, size.height / 2f)
        val farthest = hypot(screenCenter.x, screenCenter.y)

        forEachCoverCell { row, column, _, center, cellSize ->
            val startAt =
                hypot(center.x - screenCenter.x, center.y - screenCenter.y) / farthest * SPREAD
            val local = ((open.value - startAt) / (1f - SPREAD)).coerceIn(0f, 1f)
            val scale = 1f - local
            if (scale <= 0f) return@forEachCoverCell

            val color = palette[(row * 7 + column * 13 + row * column) % palette.size]
            val side = (cellSize - gap) * scale
            drawRoundRect(
                color = color,
                topLeft = Offset(center.x - side / 2f, center.y - side / 2f),
                size = Size(side, side),
                cornerRadius = corner,
                alpha = scale,
            )
        }
    }
}
