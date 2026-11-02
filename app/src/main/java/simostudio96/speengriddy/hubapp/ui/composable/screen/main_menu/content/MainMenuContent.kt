package simostudio96.speengriddy.hubapp.ui.composable.screen.main_menu.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import simostudio96.speengriddy.hubapp.R
import simostudio96.speengriddy.hubapp.ui.composable.screen.main_menu.content.buttons.secondary.SecondaryButtons
import simostudio96.speengriddy.hubapp.ui.composable.screen.main_menu.content.header.MenuHeader
import simostudio96.speengriddy.hubapp.ui.composable.shared.button.ActionButton
import simostudio96.speengriddy.hubapp.ui.composable.shared.button.ActionButtonEmphasis
import simostudio96.speengriddy.hubapp.ui.composable.shared.button.ActionButtonItem
import simostudio96.speengriddy.hubapp.ui.composable.shared.pulse.startPulse
import simostudio96.speengriddy.hubapp.ui.theme.LocalDimens

@Composable
fun MainMenuContent(
    modifier: Modifier = Modifier,
    onPuzzleSelectionScreenNavigation: () -> Unit,
    onGameRecordsScreenNavigation: () -> Unit,
    onAboutScreenNavigation: () -> Unit,
    onExit: () -> Unit,
) {
    val dimens = LocalDimens.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimens.screenPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        MenuHeader(modifier = Modifier.padding(bottom = dimens.sectionGap))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 360.dp),
            verticalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
        ) {
            ActionButton(
                item = ActionButtonItem(
                    text = stringResource(R.string.main_menu_play),
                    icon = Icons.Filled.Extension,
                ),
                onClick = onPuzzleSelectionScreenNavigation,
                modifier = Modifier.startPulse(cornerRadius = dimens.controlCornerRadius),
            )
            SecondaryButtons(
                recordsItem = ActionButtonItem(
                    text = stringResource(R.string.main_menu_game_records),
                    icon = Icons.Filled.Leaderboard,
                ),
                aboutItem = ActionButtonItem(
                    text = stringResource(R.string.main_menu_about),
                    icon = Icons.AutoMirrored.Filled.HelpOutline,
                ),
                onRecordsClick = onGameRecordsScreenNavigation,
                onAboutClick = onAboutScreenNavigation,
            )
            ActionButton(
                item = ActionButtonItem(
                    text = stringResource(R.string.main_menu_exit),
                    icon = Icons.AutoMirrored.Filled.ExitToApp,
                    emphasis = ActionButtonEmphasis.Alternate,
                ),
                onClick = onExit,
            )
        }
    }
}
