package simostudio96.speengriddy.hubapp.ui.composable.screen.game.swap

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.zIndex
import simostudio96.speengriddy.hubapp.ui.composable.screen.game.swap.rememberPieceMotion

private const val MOVING_Z_INDEX = 1f

@Composable
fun Modifier.pieceMotion(target: IntOffset): Modifier {
    val motion = rememberPieceMotion(
        target = target,
        spec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessMediumLow,
        ),
    )

    return this
        .zIndex(if (motion.isMoving) MOVING_Z_INDEX else 0f)
        .offset { motion.linearOffset() }
}
