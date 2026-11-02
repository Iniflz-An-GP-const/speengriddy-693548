package simostudio96.speengriddy.hubapp.ui.theme.motion

import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.spring

data class Motion(
    /**
     * How strongly pages react to the swipe — scale, tilt, shift of the neighbours.
     * 0 keeps them flat, 1 is the effect as each pager variant designed it, above 1 exaggerates it.
     */
    val pageEffectStrength: Float,
    val settleDampingRatio: Float,
    val settleStiffness: Float,
    val crossfadeMillis: Int,
) {
    val settleSpec: SpringSpec<Float>
        get() = spring(dampingRatio = settleDampingRatio, stiffness = settleStiffness)
}
