package simostudio96.speengriddy.hubapp.ui.composable.app_root.background

import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate
import kotlin.math.ceil

/**
 * Repeats a pattern tile of [tile] pixels over the whole canvas. [shift] (0..1) slides the pattern
 * diagonally by that part of a tile; because the pattern repeats every tile, the slide is seamless
 * when [shift] wraps from 1 back to 0.
 */
internal fun DrawScope.drawTiled(tile: Float, shift: Float, drawTile: DrawScope.() -> Unit) {
    val columns = ceil(size.width / tile).toInt() + 1
    val rows = ceil(size.height / tile).toInt() + 1
    val offset = shift * tile

    for (row in -1 until rows) {
        for (column in -1 until columns) {
            translate(left = column * tile + offset, top = row * tile + offset) { drawTile() }
        }
    }
}
