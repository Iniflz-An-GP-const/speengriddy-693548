package simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.pager

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import simostudio96.speengriddy.hubapp.data.model.Puzzle
import simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.pager.PuzzlePage
import simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.pager.rememberPagerFling
import simostudio96.speengriddy.hubapp.ui.theme.LocalMotion
import kotlin.math.abs

private val PeekWidth = 44.dp
private val PageGap = 8.dp
private const val SideScale = 0.82f
private const val SideAlpha = 0.45f

@Composable
fun PuzzlePager(
    puzzles: List<Puzzle>,
    pagerState: PagerState,
    gridSize: Int,
    modifier: Modifier = Modifier,
) {
    val strength = LocalMotion.current.pageEffectStrength

    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = PeekWidth),
        pageSpacing = PageGap,
        flingBehavior = rememberPagerFling(pagerState),
        key = { puzzles[it].id },
    ) { page ->
        PuzzlePage(
            puzzle = puzzles[page],
            gridSize = gridSize,
            modifier = Modifier.graphicsLayer {
                val distance =
                    abs(pagerState.currentPage - page + pagerState.currentPageOffsetFraction)
                        .coerceIn(0f, 1f)
                val scale = (1f - (1f - SideScale) * strength * distance).coerceIn(0f, 1f)
                scaleX = scale
                scaleY = scale
                alpha = (1f - (1f - SideAlpha) * strength * distance).coerceIn(0f, 1f)
            },
        )
    }
}
