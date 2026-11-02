package simostudio96.speengriddy.hubapp.ui.composable.screen.game_records.card

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import simostudio96.speengriddy.hubapp.ui.theme.LocalDimens

internal object GameRecordCardDefaults {
    val shape: Shape
        @Composable get() = RoundedCornerShape(LocalDimens.current.cardCornerRadius)

    val padding: Dp
        @Composable get() = LocalDimens.current.cardPadding
}
