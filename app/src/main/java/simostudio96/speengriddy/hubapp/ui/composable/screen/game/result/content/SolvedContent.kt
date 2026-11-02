package simostudio96.speengriddy.hubapp.ui.composable.screen.game.result.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import simostudio96.speengriddy.hubapp.R
import simostudio96.speengriddy.hubapp.ui.composable.screen.game.result.SolvedComparison
import simostudio96.speengriddy.hubapp.ui.composable.screen.game.result.SolvedInfo
import simostudio96.speengriddy.hubapp.ui.theme.LocalDimens

private val DividerHeight = 36.dp

@Composable
fun SolvedContent(
    info: SolvedInfo,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val isNewRecord = info.comparison is SolvedComparison.NewRecord

    val (bestLabel, bestValue) = when (val comparison = info.comparison) {
        SolvedComparison.First -> stringResource(R.string.game_solved_label_best) to stringResource(
            R.string.game_solved_no_value
        )

        is SolvedComparison.NewRecord -> stringResource(R.string.game_solved_label_previous_best) to comparison.previousBestText
        is SolvedComparison.NotBest -> stringResource(R.string.game_solved_label_best) to comparison.bestText
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            StatColumn(
                value = info.timeText,
                label = stringResource(R.string.game_solved_label_time),
                valueColor = if (isNewRecord) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f),
            )
            VerticalDivider(
                modifier = Modifier.height(DividerHeight),
                color = MaterialTheme.colorScheme.outlineVariant
            )
            StatColumn(
                value = bestValue,
                label = bestLabel,
                modifier = Modifier.weight(1f),
            )
            VerticalDivider(
                modifier = Modifier.height(DividerHeight),
                color = MaterialTheme.colorScheme.outlineVariant
            )
            StatColumn(
                value = info.levelLabel,
                label = stringResource(R.string.game_solved_label_level),
                modifier = Modifier.weight(1f),
            )
        }

        Text(
            text = "${info.puzzleTitle} · ${info.gridLabel}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        if (isNewRecord) {
            Text(
                text = stringResource(R.string.game_solved_new_record),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Composable
private fun StatColumn(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
    valueColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    val dimens = LocalDimens.current

    Column(
        modifier = modifier.padding(horizontal = dimens.tightSpacing),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimens.tightSpacing),
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium.copy(fontFeatureSettings = "tnum"),
            color = valueColor,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
