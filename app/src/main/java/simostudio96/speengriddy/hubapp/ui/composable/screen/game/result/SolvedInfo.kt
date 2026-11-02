package simostudio96.speengriddy.hubapp.ui.composable.screen.game.result

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.ImageBitmap

@Immutable
sealed interface SolvedComparison {
    data object First : SolvedComparison

    data class NewRecord(val previousBestText: String) : SolvedComparison

    data class NotBest(val bestText: String) : SolvedComparison
}

@Immutable
data class SolvedInfo(
    val title: String,
    val puzzleTitle: String,
    val levelLabel: String,
    val gridLabel: String,
    val timeText: String,
    val comparison: SolvedComparison,
    val picture: ImageBitmap,
)
