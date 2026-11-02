package simostudio96.speengriddy.hubapp.ui.composable.screen.game_records.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import simostudio96.speengriddy.hubapp.ui.composable.screen.game_records.filter.GameRecordFilterItem
import simostudio96.speengriddy.hubapp.ui.composable.screen.game_records.filter.chip.GameRecordFilterChip
import simostudio96.speengriddy.hubapp.ui.theme.LocalDimens

@Composable
fun GameRecordFilter(
    items: List<GameRecordFilterItem>,
    onSelect: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = dimens.screenPadding),
        horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
    ) {
        itemsIndexed(items) { index, item ->
            GameRecordFilterChip(item = item, onClick = { onSelect(index) })
        }
    }
}
