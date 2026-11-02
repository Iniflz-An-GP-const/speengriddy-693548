package simostudio96.speengriddy.hubapp.ui.composable.screen.splash.entrance

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import simostudio96.speengriddy.hubapp.ui.composable.screen.splash.SplashLockupSlot
import simostudio96.speengriddy.hubapp.ui.composable.screen.splash.SplashLogo
import simostudio96.speengriddy.hubapp.ui.composable.screen.splash.SplashTitle
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

private const val TITLE_START_DELAY_MS = 400L
private const val HOLD_MS = 1200L

private val FlyDistance = 150.dp
private const val START_ROTATION = -45f
private const val START_SCALE = 1.3f
private const val TITLE_START_SCALE = 0.9f

@Composable
fun SplashEntrance(
    isStarted: Boolean,
    lockup: SplashLockupSlot,
    onFinished: () -> Unit,
) {
    val pieceOffset = remember { Animatable(1f) }
    val pieceRotation = remember { Animatable(START_ROTATION) }
    val pieceScale = remember { Animatable(START_SCALE) }
    val titleAlpha = remember { Animatable(0f) }
    val titleScale = remember { Animatable(TITLE_START_SCALE) }

    LaunchedEffect(isStarted) {
        if (!isStarted) return@LaunchedEffect

        coroutineScope {
            launch {
                pieceOffset.animateTo(
                    targetValue = 0f,
                    animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessMedium),
                )
            }
            launch {
                pieceRotation.animateTo(
                    targetValue = 0f,
                    animationSpec = spring(Spring.DampingRatioMediumBouncy, Spring.StiffnessLow),
                )
            }
            launch {
                pieceScale.animateTo(
                    targetValue = 1f,
                    animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessMedium),
                )
            }
            launch {
                delay(TITLE_START_DELAY_MS.milliseconds)
                launch {
                    titleAlpha.animateTo(
                        1f,
                        tween(durationMillis = 300, easing = LinearOutSlowInEasing)
                    )
                }
                launch {
                    titleScale.animateTo(
                        targetValue = 1f,
                        animationSpec = spring(
                            Spring.DampingRatioMediumBouncy,
                            Spring.StiffnessMedium
                        ),
                    )
                }
            }
        }

        delay(HOLD_MS.milliseconds)
        onFinished()
    }

    lockup(
        { size ->
            SplashLogo(
                size = size,
                modifier = Modifier.graphicsLayer {
                    translationX = -pieceOffset.value * FlyDistance.toPx()
                    translationY = -pieceOffset.value * FlyDistance.toPx()
                    rotationZ = pieceRotation.value
                    scaleX = pieceScale.value
                    scaleY = pieceScale.value
                },
            )
        },
        {
            SplashTitle(
                modifier = Modifier.graphicsLayer {
                    alpha = titleAlpha.value
                    scaleX = titleScale.value
                    scaleY = titleScale.value
                },
            )
        },
    )
}
