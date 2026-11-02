package simostudio96.speengriddy.hubapp.ui.composable.shared.pulse

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import simostudio96.speengriddy.hubapp.ui.composable.shared.pulse.PulseTiming

private const val BREATH_SCALE_DELTA = 0.04f

@Composable
@Suppress("UNUSED_PARAMETER")
fun Modifier.pulseEffect(cornerRadius: Dp, timing: PulseTiming): Modifier {
    if (timing.isOff) return this

    val peak = 1f + BREATH_SCALE_DELTA * timing.intensity
    val scale = rememberInfiniteTransition(label = "startBreath").animateFloat(
        initialValue = 1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = timing.cycleMs
                1f at 0
                peak at timing.durationMs / 2 using FastOutSlowInEasing
                1f at timing.durationMs using FastOutSlowInEasing
                if (timing.pauseMs > 0) 1f at timing.cycleMs
            },
            repeatMode = RepeatMode.Restart,
        ),
        label = "startBreathScale",
    )

    return graphicsLayer {
        scaleX = scale.value
        scaleY = scale.value
    }
}
