package simostudio96.speengriddy.hubapp.ui.composable.shared.button

import androidx.compose.ui.graphics.vector.ImageVector

enum class ActionButtonEmphasis {
    Default,
    Alternate,
}

data class ActionButtonItem(
    val text: String,
    val icon: ImageVector,
    val emphasis: ActionButtonEmphasis = ActionButtonEmphasis.Default,
    val enabled: Boolean = true,
)
