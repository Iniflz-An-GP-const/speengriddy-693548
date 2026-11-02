package simostudio96.speengriddy.hubapp.ui.composable.shared.button

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import simostudio96.speengriddy.hubapp.ui.theme.LocalDimens

internal object ActionButtonDefaults {
    const val DISABLED_ALPHA: Float = 0.38f
    const val LABEL_MAX_LINES: Int = 2


    val Height: Dp = 56.dp
    val IconSize: Dp = 24.dp

    val horizontalPadding: Dp
        @Composable get() = LocalDimens.current.cardPadding

    val iconTextSpacing: Dp
        @Composable get() = LocalDimens.current.itemSpacing

    val shape: Shape
        @Composable get() = RoundedCornerShape(LocalDimens.current.controlCornerRadius)

    @Composable
    fun containerColor(emphasis: ActionButtonEmphasis): Color = when (emphasis) {
        ActionButtonEmphasis.Default -> MaterialTheme.colorScheme.primaryContainer
        ActionButtonEmphasis.Alternate -> MaterialTheme.colorScheme.tertiaryContainer
    }

    @Composable
    fun onContainerColor(emphasis: ActionButtonEmphasis): Color = when (emphasis) {
        ActionButtonEmphasis.Default -> MaterialTheme.colorScheme.onPrimaryContainer
        ActionButtonEmphasis.Alternate -> MaterialTheme.colorScheme.onTertiaryContainer
    }

    @Composable
    fun accentColor(emphasis: ActionButtonEmphasis): Color = when (emphasis) {
        ActionButtonEmphasis.Default -> MaterialTheme.colorScheme.primary
        ActionButtonEmphasis.Alternate -> MaterialTheme.colorScheme.tertiary
    }

    @Composable
    fun onAccentColor(emphasis: ActionButtonEmphasis): Color = when (emphasis) {
        ActionButtonEmphasis.Default -> MaterialTheme.colorScheme.onPrimary
        ActionButtonEmphasis.Alternate -> MaterialTheme.colorScheme.onTertiary
    }

    @Composable
    fun labelStyle(): TextStyle = MaterialTheme.typography.titleMedium
}
