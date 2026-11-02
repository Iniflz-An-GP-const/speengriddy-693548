package simostudio96.speengriddy.hubapp.utils

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

/** Cuts a picture into rows x cols equal pieces, dropping the remainder at the edge. */
fun sliceImageIntoPieces(source: Bitmap, rows: Int, cols: Int): List<ImageBitmap> {
    val pieceWidth = source.width / cols
    val pieceHeight = source.height / rows

    val usableWidth = pieceWidth * cols
    val usableHeight = pieceHeight * rows
    val cropped = if (usableWidth != source.width || usableHeight != source.height) {
        Bitmap.createBitmap(source, 0, 0, usableWidth, usableHeight)
    } else {
        source
    }

    val pieces = mutableListOf<ImageBitmap>()
    for (row in 0 until rows) {
        for (col in 0 until cols) {
            val x = col * pieceWidth
            val y = row * pieceHeight
            val piece = Bitmap.createBitmap(cropped, x, y, pieceWidth, pieceHeight)
            pieces.add(piece.asImageBitmap())
        }
    }
    return pieces
}
