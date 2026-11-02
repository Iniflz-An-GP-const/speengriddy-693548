package simostudio96.speengriddy.hubapp.ui.composable.app_root.background.motion

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import simostudio96.speengriddy.hubapp.ui.composable.app_root.background.BackgroundMotion

private const val DRIFT_MILLIS = 36_000

@Composable
fun rememberBackgroundMotion(): BackgroundMotion {
    val shift = rememberInfiniteTransition(label = "backgroundDrift").animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(DRIFT_MILLIS, easing = LinearEasing)
        ),
        label = "shift",
    )
    return remember { BackgroundMotion(shift = { shift.value }, alpha = { 1f }) }
}
