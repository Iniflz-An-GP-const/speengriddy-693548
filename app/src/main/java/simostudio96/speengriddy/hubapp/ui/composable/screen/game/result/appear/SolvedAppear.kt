package simostudio96.speengriddy.hubapp.ui.composable.screen.game.result.appear

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

private const val START_SCALE = 0.8f

@Composable
fun Modifier.solvedAppear(): Modifier {
    val progress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        progress.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMediumLow,
            ),
        )
    }

    return graphicsLayer {
        val scale = START_SCALE + (1f - START_SCALE) * progress.value
        scaleX = scale
        scaleY = scale
        alpha = (progress.value * 2f).coerceIn(0f, 1f)
    }
}
