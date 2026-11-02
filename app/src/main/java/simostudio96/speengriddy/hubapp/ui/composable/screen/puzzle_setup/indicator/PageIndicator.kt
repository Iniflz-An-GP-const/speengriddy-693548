package simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.indicator

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import simostudio96.speengriddy.hubapp.R
import simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.indicator.PageIndicatorDefaults
import simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.indicator.PageIndicatorPlaceholder
import simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.indicator.pagePositionSemantics
import simostudio96.speengriddy.hubapp.ui.theme.LocalMotion

@Composable
fun PageIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    if (PageIndicatorDefaults.hasNothingToShow(pagerState)) {
        PageIndicatorPlaceholder(modifier)
        return
    }

    val durationMillis = LocalMotion.current.crossfadeMillis
    val numberStyle = MaterialTheme.typography.titleSmall.copy(fontFeatureSettings = "tnum")

    Row(
        modifier = modifier
            .pagePositionSemantics(pagerState)
            .height(PageIndicatorDefaults.Height),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        AnimatedContent(
            targetState = pagerState.currentPage + 1,
            transitionSpec = {
                val forward = targetState > initialState
                (slideInVertically(tween(durationMillis)) { height -> if (forward) height else -height } +
                        fadeIn(tween(durationMillis))) togetherWith
                        (slideOutVertically(tween(durationMillis)) { height -> if (forward) -height else height } +
                                fadeOut(tween(durationMillis)))
            },
            label = "pageCounter",
        ) { number ->
            Text(
                text = number.toString(),
                style = numberStyle,
                color = MaterialTheme.colorScheme.primary,
            )
        }

        Text(
            text = stringResource(R.string.puzzle_counter_total, pagerState.pageCount),
            style = numberStyle,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
