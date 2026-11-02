package simostudio96.speengriddy.hubapp.ui.composable.screen.splash.lockup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import simostudio96.speengriddy.hubapp.ui.theme.LocalDimens

private val LogoSize = 72.dp

@Composable
fun SplashLockup(
    logo: @Composable (size: Dp) -> Unit,
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Row(
        modifier = modifier.padding(horizontal = dimens.screenPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            dimens.itemSpacing,
            Alignment.CenterHorizontally
        ),
    ) {
        logo(LogoSize)

        Box(modifier = Modifier.weight(1f, fill = false)) {
            title()
        }
    }
}
