package simostudio96.speengriddy.hubapp.ui.composable.shared.level

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material.icons.filled.SentimentNeutral
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import simostudio96.speengriddy.hubapp.data.model.PuzzleLevel

val PuzzleLevel.icon: ImageVector
    get() = when (this) {
        PuzzleLevel.EASY -> Icons.Filled.SentimentSatisfied
        PuzzleLevel.MEDIUM -> Icons.Filled.SentimentNeutral
        PuzzleLevel.HARD -> Icons.Filled.SentimentDissatisfied
        PuzzleLevel.EXPERT -> Icons.Filled.Whatshot
    }

data class LevelColors(
    val container: Color,
    val onContainer: Color,
    val accent: Color,
)

@Composable
fun PuzzleLevel.colors(): LevelColors = when (this) {
    PuzzleLevel.EASY -> LevelColors(
        container = MaterialTheme.colorScheme.secondaryContainer,
        onContainer = MaterialTheme.colorScheme.onSecondaryContainer,
        accent = MaterialTheme.colorScheme.secondary,
    )

    PuzzleLevel.MEDIUM -> LevelColors(
        container = MaterialTheme.colorScheme.primaryContainer,
        onContainer = MaterialTheme.colorScheme.onPrimaryContainer,
        accent = MaterialTheme.colorScheme.primary,
    )

    PuzzleLevel.HARD -> LevelColors(
        container = MaterialTheme.colorScheme.tertiaryContainer,
        onContainer = MaterialTheme.colorScheme.onTertiaryContainer,
        accent = MaterialTheme.colorScheme.tertiary,
    )

    PuzzleLevel.EXPERT -> LevelColors(
        container = MaterialTheme.colorScheme.errorContainer,
        onContainer = MaterialTheme.colorScheme.onErrorContainer,
        accent = MaterialTheme.colorScheme.error,
    )
}
