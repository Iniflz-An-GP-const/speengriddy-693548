package simostudio96.speengriddy.hubapp.ui.composable.shared.button.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import simostudio96.speengriddy.hubapp.ui.composable.shared.button.ActionButtonDefaults
import simostudio96.speengriddy.hubapp.ui.composable.shared.button.ActionButtonItem
import simostudio96.speengriddy.hubapp.ui.theme.LocalDimens

private val TileSize = 36.dp
private const val TILE_ALPHA = 0.16f

@Composable
fun ActionButtonContent(
    item: ActionButtonItem,
    contentColor: Color,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(ActionButtonDefaults.iconTextSpacing),
    ) {
        Box(
            modifier = Modifier
                .size(TileSize)
                .background(
                    color = contentColor.copy(alpha = TILE_ALPHA),
                    shape = RoundedCornerShape(LocalDimens.current.cornerRadius),
                ),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(ActionButtonDefaults.IconSize),
            )
        }
        Text(
            text = item.text,
            color = contentColor,
            style = ActionButtonDefaults.labelStyle(),
            textAlign = TextAlign.Center,
            maxLines = ActionButtonDefaults.LABEL_MAX_LINES,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f),
        )
    }
}
