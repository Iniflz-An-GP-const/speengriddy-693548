package simostudio96.speengriddy.hubapp.ui.theme.motion

import androidx.compose.animation.core.Spring
import simostudio96.speengriddy.hubapp.ui.theme.motion.Motion

val AppMotion = Motion(
    pageEffectStrength = 1.4f,
    settleDampingRatio = Spring.DampingRatioLowBouncy,
    settleStiffness = Spring.StiffnessMediumLow,
    crossfadeMillis = 400,
)
