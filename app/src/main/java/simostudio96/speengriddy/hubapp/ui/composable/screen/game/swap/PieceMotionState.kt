package simostudio96.speengriddy.hubapp.ui.composable.screen.game.swap

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

@Stable
class PieceMotion internal constructor(initial: IntOffset) {
    var start: IntOffset by mutableStateOf(initial)
        private set
    var end: IntOffset by mutableStateOf(initial)
        private set

    private val progressAnimatable = Animatable(1f)

    val progress: Float get() = progressAnimatable.value

    val isMoving: Boolean get() = progressAnimatable.isRunning

    fun linearOffset(): IntOffset = IntOffset(
        x = (start.x + (end.x - start.x) * progress).roundToInt(),
        y = (start.y + (end.y - start.y) * progress).roundToInt(),
    )

    internal suspend fun moveTo(target: IntOffset, spec: AnimationSpec<Float>) {
        if (target == end) return

        start = linearOffset()
        end = target
        progressAnimatable.snapTo(0f)
        progressAnimatable.animateTo(1f, spec)
    }
}

/**
 * Keeps a piece at [target] and moves it there with [spec] whenever the target changes.
 * The first position is taken at once, without a move.
 */
@Composable
fun rememberPieceMotion(target: IntOffset, spec: AnimationSpec<Float>): PieceMotion {
    val motion = remember { PieceMotion(initial = target) }

    LaunchedEffect(target) {
        motion.moveTo(target, spec)
    }
    return motion
}
