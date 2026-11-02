package simostudio96.speengriddy.hubapp.ui.composable.screen.game.hint

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import simostudio96.speengriddy.hubapp.R
import simostudio96.speengriddy.hubapp.ui.theme.LocalDimens

@Composable
@Suppress("UNUSED_PARAMETER")
fun HintDialog(
    image: ImageBitmap,
    title: String,
    onDismiss: () -> Unit,
) {
    val dimens = LocalDimens.current

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(dimens.cardCornerRadius),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 4.dp,
        ) {
            Image(
                bitmap = image,
                contentDescription = stringResource(R.string.game_preview_description),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .clickable(onClick = onDismiss)
                    .padding(dimens.itemSpacing),
            )
        }
    }
}
