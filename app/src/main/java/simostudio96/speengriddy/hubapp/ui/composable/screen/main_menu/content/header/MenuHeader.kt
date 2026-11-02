package simostudio96.speengriddy.hubapp.ui.composable.screen.main_menu.content.header

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import simostudio96.speengriddy.hubapp.R

@Composable
fun MenuHeader(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.app_name),
        style = MaterialTheme.typography.displaySmall,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier,
    )
}
