package simostudio96.speengriddy.hubapp.ui.composable.screen.game.result.shell

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import simostudio96.speengriddy.hubapp.ui.composable.screen.game.result.SolvedInfo
import simostudio96.speengriddy.hubapp.ui.theme.LocalDimens

private val TrophyBadgeSize = 72.dp
private val TrophyIconSize = 40.dp

@Composable
fun SolvedShell(
    info: SolvedInfo,
    content: @Composable (SolvedInfo) -> Unit,
    actions: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(dimens.cardCornerRadius),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 6.dp,
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(dimens.cardPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimens.contentSpacing),
        ) {
            Box(
                modifier = Modifier
                    .size(TrophyBadgeSize)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Filled.EmojiEvents,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(TrophyIconSize),
                )
            }

            Text(
                text = info.title,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
            )

            content(info)

            actions()
        }
    }
}
