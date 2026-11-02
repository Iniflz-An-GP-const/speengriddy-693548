package simostudio96.speengriddy.hubapp.ui.composable.screen.game_records.filter

import androidx.compose.ui.graphics.vector.ImageVector

data class GameRecordFilterItem(
    val label: String,
    val selected: Boolean,
    val icon: ImageVector? = null,
)
