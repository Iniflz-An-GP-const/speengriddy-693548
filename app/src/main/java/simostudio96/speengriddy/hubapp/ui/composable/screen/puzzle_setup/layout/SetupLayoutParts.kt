package simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import simostudio96.speengriddy.hubapp.ui.theme.LocalDimens

private val ControlsMaxWidth = 400.dp

@Composable
internal fun SetupLayoutScaffold(
    topBar: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    val dimens = LocalDimens.current

    Column(modifier = modifier.fillMaxSize()) {
        topBar()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = dimens.contentSpacing),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimens.contentSpacing),
            content = content,
        )
    }
}

@Composable
internal fun ControlsBlock(
    content: @Composable ColumnScope.() -> Unit,
) {
    val dimens = LocalDimens.current

    Column(
        modifier = Modifier
            .widthIn(max = ControlsMaxWidth)
            .fillMaxWidth()
            .padding(horizontal = dimens.screenPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimens.contentSpacing),
        content = content,
    )
}
