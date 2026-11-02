package simostudio96.speengriddy.hubapp.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import simostudio96.speengriddy.hubapp.data.model.Puzzle

const val FULL_SIZE_MAX_DIMENSION = 1920

const val THUMBNAIL_MAX_DIMENSION = 400

/** Decodes the picture into a software bitmap: a hardware one cannot be passed to Bitmap.createBitmap(), which the slicing uses. [maxDimension] caps the longest side. Blocking: call it on Dispatchers.IO. */
fun loadPuzzleBitmap(
    resources: Resources,
    puzzle: Puzzle,
    maxDimension: Int = FULL_SIZE_MAX_DIMENSION
): Bitmap {
    val source = ImageDecoder.createSource(resources, puzzle.resId)
    return ImageDecoder.decodeBitmap(source) { decoder, info, _ ->
        decoder.allocator = ImageDecoder.ALLOCATOR_SOFTWARE
        val longestSide = maxOf(info.size.width, info.size.height)
        if (longestSide > maxDimension) {
            decoder.setTargetSampleSize(nearestSampleSize(longestSide, maxDimension))
        }
    }
}

private fun nearestSampleSize(longestSide: Int, target: Int): Int {
    var sampleSize = 1
    while (longestSide / (sampleSize * 2) >= target) {
        sampleSize *= 2
    }
    return sampleSize
}
