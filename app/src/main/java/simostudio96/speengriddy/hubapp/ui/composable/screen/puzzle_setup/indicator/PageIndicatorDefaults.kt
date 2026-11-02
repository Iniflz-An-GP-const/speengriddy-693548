package simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.indicator

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import simostudio96.speengriddy.hubapp.R

internal object PageIndicatorDefaults {
    /** Every variant takes exactly this height, so the screen
     * does not jump when the variant or the page count changes. */
    val Height: Dp = 24.dp

    fun hasNothingToShow(pagerState: PagerState): Boolean = pagerState.pageCount <= 1
}

@Composable
internal fun PageIndicatorPlaceholder(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(PageIndicatorDefaults.Height))
}

/**
 * Replaces the drawing with a spoken position, e.g. "Puzzle 3 of 20", for screen readers.
 * Reads only the current page, so it does not recompose while the pager is being dragged.
 */
@Composable
internal fun Modifier.pagePositionSemantics(pagerState: PagerState): Modifier {
    val description = stringResource(
        R.string.puzzle_position_description,
        pagerState.currentPage + 1,
        pagerState.pageCount,
    )
    return clearAndSetSemantics { contentDescription = description }
}
