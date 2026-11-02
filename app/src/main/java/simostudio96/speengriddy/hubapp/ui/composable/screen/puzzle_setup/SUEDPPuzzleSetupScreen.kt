package simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import simostudio96.speengriddy.hubapp.R
import simostudio96.speengriddy.hubapp.data.model.PuzzleLevel
import simostudio96.speengriddy.hubapp.ui.composable.app_root.topbar.AppTopBar
import simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.indicator.PageIndicator
import simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.layout.PuzzleSetupLayout
import simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.level_selector.LevelSelector
import simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.pager.PuzzlePager
import simostudio96.speengriddy.hubapp.ui.composable.screen.puzzle_setup.picker.PuzzlePickerDialog
import simostudio96.speengriddy.hubapp.ui.composable.shared.button.ActionButton
import simostudio96.speengriddy.hubapp.ui.composable.shared.button.ActionButtonItem
import simostudio96.speengriddy.hubapp.ui.theme.LocalMotion
import simostudio96.speengriddy.hubapp.ui.viewmodel.PuzzleSetupViewModel
import simostudio96.speengriddy.hubapp.utils.formatDuration
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun SUEDPPuzzleSetupScreen(
    onStartGame: (puzzleId: Int, level: PuzzleLevel) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PuzzleSetupViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val motion = LocalMotion.current
    val scope = rememberCoroutineScope()

    val pagerState =
        rememberPagerState(initialPage = uiState.selectedPuzzleIndex) { uiState.puzzles.size }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect(viewModel::onPuzzleSelected)
    }

    PuzzleSetupLayout(
        modifier = modifier,
        topBar = {
            AppTopBar(
                title = stringResource(R.string.puzzle_setup_title),
                onNavigateBack = onBackClick,
                actions = {
                    IconButton(onClick = { viewModel.onPickerVisibilityChange(true) }) {
                        Icon(
                            imageVector = Icons.Filled.GridView,
                            contentDescription = stringResource(R.string.puzzle_picker_description),
                        )
                    }
                },
            )
        },
        pager = { pagerModifier ->
            PuzzlePager(
                puzzles = uiState.puzzles,
                pagerState = pagerState,
                gridSize = uiState.selectedLevel.gridSize,
                modifier = pagerModifier,
            )
        },
        puzzleTitle = {
            Text(
                text = uiState.selectedPuzzle?.title.orEmpty(),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
            )
        },
        indicator = { PageIndicator(pagerState = pagerState) },
        levelSelector = {
            LevelSelector(
                levels = PuzzleLevel.entries,
                selected = uiState.selectedLevel,
                onSelect = viewModel::onLevelSelected,
            )
        },
        bestTime = {
            Text(
                text = uiState.bestTimeSeconds
                    ?.let { stringResource(R.string.puzzle_setup_best_time, formatDuration(it)) }
                    ?: stringResource(R.string.puzzle_setup_no_record),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        },
        startButton = {
            ActionButton(
                item = ActionButtonItem(
                    text = stringResource(R.string.puzzle_setup_start),
                    icon = Icons.Filled.PlayArrow,
                    enabled = uiState.selectedPuzzle != null,
                ),
                onClick = {
                    uiState.selectedPuzzle?.let { onStartGame(it.id, uiState.selectedLevel) }
                },
            )
        },
    )

    if (uiState.isPickerVisible) {
        PuzzlePickerDialog(
            puzzles = uiState.puzzles,
            selectedIndex = uiState.selectedPuzzleIndex,
            onSelect = { index ->
                viewModel.onPickerVisibilityChange(false)
                scope.launch {
                    pagerState.animateScrollToPage(
                        index,
                        animationSpec = motion.settleSpec
                    )
                }
            },
            onDismiss = { viewModel.onPickerVisibilityChange(false) },
        )
    }
}
