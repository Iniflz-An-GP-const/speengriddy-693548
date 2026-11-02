package simostudio96.speengriddy.hubapp.ui.composable.screen.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import simostudio96.speengriddy.hubapp.ui.theme.LocalDimens

/**
 * Which sides of a piece touch the piece that really belongs next to it.
 * Such sides have no gap and no outline: the right neighbours grow into one picture.
 */
@Immutable
data class JoinedSides(
    val start: Boolean = false,
    val top: Boolean = false,
    val end: Boolean = false,
    val bottom: Boolean = false,
    /**
     * A corner where two joined sides meet but the piece diagonally across it is not in place, so
     * the four pieces do not make a full block. The corner has to be cut out by one gap, or it
     * would stick out into the gap between the pieces.
     */
    val notchTopStart: Boolean = false,
    val notchTopEnd: Boolean = false,
    val notchBottomEnd: Boolean = false,
    val notchBottomStart: Boolean = false,
)

@Composable
fun PuzzleCell(
    piece: ImageBitmap,
    joined: JoinedSides,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val gap = dimens.pieceGap
    val outlineWidth = dimens.pieceBorderWidth
    val outlineColor = MaterialTheme.colorScheme.outlineVariant

    val left = if (joined.start) 0.dp else gap
    val top = if (joined.top) 0.dp else gap
    val right = if (joined.end) 0.dp else gap
    val bottom = if (joined.bottom) 0.dp else gap

    Box(
        modifier = modifier
            .clip(
                PieceShape(
                    left = left,
                    top = top,
                    right = right,
                    bottom = bottom,
                    notchSize = gap,
                    notchTopStart = joined.notchTopStart,
                    notchTopEnd = joined.notchTopEnd,
                    notchBottomEnd = joined.notchBottomEnd,
                    notchBottomStart = joined.notchBottomStart,
                ),
            )
            .drawWithContent {
                drawContent()

                val stroke = outlineWidth.toPx()
                if (stroke <= 0f) return@drawWithContent

                val half = stroke / 2f
                val x0 = left.toPx()
                val y0 = top.toPx()
                val x1 = size.width - right.toPx()
                val y1 = size.height - bottom.toPx()

                if (!joined.top) {
                    drawLine(outlineColor, Offset(x0, y0 + half), Offset(x1, y0 + half), stroke)
                }
                if (!joined.end) {
                    drawLine(outlineColor, Offset(x1 - half, y0), Offset(x1 - half, y1), stroke)
                }
                if (!joined.bottom) {
                    drawLine(outlineColor, Offset(x0, y1 - half), Offset(x1, y1 - half), stroke)
                }
                if (!joined.start) {
                    drawLine(outlineColor, Offset(x0 + half, y0), Offset(x0 + half, y1), stroke)
                }
            }
            .clickable(enabled = enabled, onClick = onClick),
    ) {
        Image(
            bitmap = piece,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

/**
 * The part of a cell that is shown: the cell without the gaps on its free sides, and with a
 * square of [notchSize] cut out of the chosen corners. The sides are absolute (left is left even
 * in a right-to-left layout), because the picture is not mirrored.
 */
private class PieceShape(
    private val left: Dp,
    private val top: Dp,
    private val right: Dp,
    private val bottom: Dp,
    private val notchSize: Dp,
    private val notchTopStart: Boolean,
    private val notchTopEnd: Boolean,
    private val notchBottomEnd: Boolean,
    private val notchBottomStart: Boolean,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val n = with(density) { notchSize.toPx() }
        val x0 = with(density) { left.toPx() }
        val y0 = with(density) { top.toPx() }
        val x1 = size.width - with(density) { right.toPx() }
        val y1 = size.height - with(density) { bottom.toPx() }

        val path = Path().apply {
            moveTo(if (notchTopStart) x0 + n else x0, y0)
            lineTo(if (notchTopEnd) x1 - n else x1, y0)
            if (notchTopEnd) {
                lineTo(x1 - n, y0 + n)
                lineTo(x1, y0 + n)
            }
            lineTo(x1, if (notchBottomEnd) y1 - n else y1)
            if (notchBottomEnd) {
                lineTo(x1 - n, y1 - n)
                lineTo(x1 - n, y1)
            }
            lineTo(if (notchBottomStart) x0 + n else x0, y1)
            if (notchBottomStart) {
                lineTo(x0 + n, y1 - n)
                lineTo(x0, y1 - n)
            }
            lineTo(x0, if (notchTopStart) y0 + n else y0)
            if (notchTopStart) {
                lineTo(x0 + n, y0 + n)
                lineTo(x0 + n, y0)
            }
            close()
        }
        return Outline.Generic(path)
    }
}
