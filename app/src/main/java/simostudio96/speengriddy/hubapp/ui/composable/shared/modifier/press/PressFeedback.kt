package simostudio96.speengriddy.hubapp.ui.composable.shared.modifier.press

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import simostudio96.speengriddy.hubapp.ui.composable.shared.modifier.press.pressEffect

@Composable
fun Modifier.pressFeedback(interactionSource: InteractionSource): Modifier =
    pressEffect(interactionSource)
