package simostudio96.speengriddy.hubapp.ui.composable.screen.game

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import simostudio96.speengriddy.hubapp.R
import simostudio96.speengriddy.hubapp.data.model.PuzzleLevel
import simostudio96.speengriddy.hubapp.ui.composable.app_root.topbar.AppTopBar
import simostudio96.speengriddy.hubapp.ui.composable.screen.game.hint.HintDialog
import simostudio96.speengriddy.hubapp.ui.composable.screen.game.layout.GameLayout
import simostudio96.speengriddy.hubapp.ui.composable.screen.game.result.SolvedComparison
import simostudio96.speengriddy.hubapp.ui.composable.screen.game.result.SolvedInfo
import simostudio96.speengriddy.hubapp.ui.composable.screen.game.result.SolvedResultDialog
import simostudio96.speengriddy.hubapp.ui.composable.shared.ConfirmationDialog
import simostudio96.speengriddy.hubapp.ui.theme.LocalDimens
import simostudio96.speengriddy.hubapp.ui.viewmodel.GameViewModel
import simostudio96.speengriddy.hubapp.ui.viewmodel.SolveSummary
import simostudio96.speengriddy.hubapp.utils.formatDuration
import org.koin.androidx.compose.koinViewModel

@Composable
fun SUEDPGameScreen(
    puzzleId: Int,
    level: PuzzleLevel,
    viewModel: GameViewModel = koinViewModel(),
    onExitToMainMenu: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    // Read on every composition, so the title follows the device language
    val puzzleTitle = uiState.puzzle?.title.orEmpty()
    val dimens = LocalDimens.current
    var isHintVisible by remember { mutableStateOf(false) }
    var isExitConfirmationVisible by remember { mutableStateOf(false) }

    LaunchedEffect(puzzleId, level) {
        viewModel.loadGame(puzzleId, level)
    }

    LifecycleEventEffect(Lifecycle.Event.ON_START) { viewModel.onScreenStarted() }
    LifecycleEventEffect(Lifecycle.Event.ON_STOP) { viewModel.onScreenStopped() }

    BackHandler { isExitConfirmationVisible = true }

    GameLayout(
        isTimerVisible = !uiState.isLoading,
        topBar = { extraAction ->
            AppTopBar(
                title = puzzleTitle,
                subtitle = stringResource(id = level.titleRes),
                onNavigateBack = { isExitConfirmationVisible = true },
                actions = {
                    extraAction()
                    if (uiState.fullImage != null) {
                        IconButton(onClick = { isHintVisible = true }) {
                            Icon(
                                imageVector = Icons.Filled.Lightbulb,
                                contentDescription = stringResource(R.string.game_hint_description),
                            )
                        }
                    }
                }
            )
        },
        timer = {
            Text(
                text = formatDuration(uiState.elapsedSeconds.toLong()),
                style = MaterialTheme.typography.titleLarge.copy(fontFeatureSettings = "tnum"),
            )
        },
        board = { boardModifier ->
            Box(modifier = boardModifier) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else {
                    PuzzleBoard(
                        uiState = uiState,
                        onCellClick = viewModel::onCellClicked,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                horizontal = dimens.itemSpacing,
                                vertical = dimens.tightSpacing
                            ),
                    )
                }
            }
        },
    )

    val fullImage = uiState.fullImage
    if (isHintVisible && fullImage != null) {
        HintDialog(
            image = fullImage,
            title = puzzleTitle,
            onDismiss = { isHintVisible = false },
        )
    }

    if (isExitConfirmationVisible) {
        ConfirmationDialog(
            title = stringResource(R.string.game_exit_title),
            message = stringResource(R.string.game_exit_message),
            onConfirm = onExitToMainMenu,
            onDismiss = { isExitConfirmationVisible = false }
        )
    }

    val solveSummary = uiState.solveSummary
    if (uiState.isSolved && solveSummary != null && fullImage != null) {
        SolvedResultDialog(
            info = solvedInfoOf(
                puzzleTitle = puzzleTitle,
                level = level,
                elapsedSeconds = uiState.elapsedSeconds.toLong(),
                summary = solveSummary,
                picture = fullImage,
            ),
            onPlayAgain = viewModel::restart,
            onMainMenu = onExitToMainMenu,
        )
    }
}

@Composable
private fun solvedInfoOf(
    puzzleTitle: String,
    level: PuzzleLevel,
    elapsedSeconds: Long,
    summary: SolveSummary,
    picture: ImageBitmap,
): SolvedInfo {
    val previousBest = summary.previousBestSeconds

    val comparison = when {
        previousBest == null -> SolvedComparison.First
        elapsedSeconds < previousBest -> SolvedComparison.NewRecord(
            previousBestText = formatDuration(
                previousBest
            )
        )

        else -> SolvedComparison.NotBest(bestText = formatDuration(previousBest))
    }

    return SolvedInfo(
        title = stringResource(R.string.game_solved_title),
        puzzleTitle = puzzleTitle,
        levelLabel = stringResource(level.titleRes),
        gridLabel = stringResource(level.gridRes),
        timeText = formatDuration(elapsedSeconds),
        comparison = comparison,
        picture = picture,
    )
}
