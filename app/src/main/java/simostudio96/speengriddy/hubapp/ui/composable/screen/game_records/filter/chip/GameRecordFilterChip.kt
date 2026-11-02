package simostudio96.speengriddy.hubapp.ui.composable.screen.game_records.filter.chip

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import simostudio96.speengriddy.hubapp.ui.composable.screen.game_records.filter.GameRecordFilterItem
import simostudio96.speengriddy.hubapp.ui.theme.LocalDimens

@Composable
fun GameRecordFilterChip(
    item: GameRecordFilterItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    FilterChip(
        selected = item.selected,
        onClick = onClick,
        label = { Text(item.label) },
        leadingIcon = item.icon?.let { icon ->
            {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(FilterChipDefaults.IconSize),
                )
            }
        },
        shape = RoundedCornerShape(dimens.controlCornerRadius),
        modifier = modifier,
    )
}
