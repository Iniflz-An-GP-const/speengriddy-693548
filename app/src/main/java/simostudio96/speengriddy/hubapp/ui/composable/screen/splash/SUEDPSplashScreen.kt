package simostudio96.speengriddy.hubapp.ui.composable.screen.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import simostudio96.speengriddy.hubapp.ui.composable.screen.splash.cover.SplashCover
import simostudio96.speengriddy.hubapp.ui.composable.screen.splash.entrance.SplashEntrance
import simostudio96.speengriddy.hubapp.ui.composable.screen.splash.lockup.SplashLockup

@Composable
fun SUEDPSplashScreen(
    modifier: Modifier = Modifier,
    onMainMenuScreenNavigation: () -> Unit,
) {
    var isEntranceStarted by remember { mutableStateOf(false) }

    SplashBackground(modifier = modifier) {
        Box(modifier = Modifier.alpha(if (isEntranceStarted) 1f else 0f)) {
            SplashEntrance(
                isStarted = isEntranceStarted,
                lockup = { logo, title -> SplashLockup(logo = logo, title = title) },
                onFinished = onMainMenuScreenNavigation,
            )
        }

        SplashCover(onRevealed = { isEntranceStarted = true })
    }
}
