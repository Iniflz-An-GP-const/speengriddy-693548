package simostudio96.speengriddy.hubapp.ui.composable.app_root.background

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import simostudio96.speengriddy.hubapp.ui.composable.app_root.background.decor.BackgroundDecor
import simostudio96.speengriddy.hubapp.ui.composable.app_root.background.motion.rememberBackgroundMotion
import simostudio96.speengriddy.hubapp.ui.composable.app_root.background.points.backgroundPoints

@Composable
fun AppBackground(modifier: Modifier = Modifier) {
    BackgroundDecor(
        motion = rememberBackgroundMotion(),
        points = ::backgroundPoints,
        modifier = modifier
    )
}
