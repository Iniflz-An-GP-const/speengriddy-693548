package simostudio96.speengriddy.hubapp.ui.viewmodel

import android.content.res.Resources
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import simostudio96.speengriddy.hubapp.data.entity.GameRecord
import simostudio96.speengriddy.hubapp.data.model.Puzzle
import simostudio96.speengriddy.hubapp.data.model.PuzzleLevel
import simostudio96.speengriddy.hubapp.data.repository.GameRecordRepository
import simostudio96.speengriddy.hubapp.data.repository.PuzzleRepository
import simostudio96.speengriddy.hubapp.utils.FULL_SIZE_MAX_DIMENSION
import simostudio96.speengriddy.hubapp.utils.loadPuzzleBitmap
import simostudio96.speengriddy.hubapp.utils.shuffledOrder
import simostudio96.speengriddy.hubapp.utils.sliceImageIntoPieces
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.milliseconds

data class GameUiState(
    val isLoading: Boolean = true,
    /** The puzzle being assembled; its title follows the device language. */
    val puzzle: Puzzle? = null,
    val cols: Int = 0,
    val pieceAspect: Float = 1f,
    val pieces: List<ImageBitmap> = emptyList(),
    val fullImage: ImageBitmap? = null,
    /** order[i] is the index of the "correct" piece that currently lies in cell i. */
    val order: List<Int> = emptyList(),
    val selectedCell: Int? = null,
    val isSolved: Boolean = false,
    val elapsedSeconds: Int = 0,
    /** Filled in a moment after the puzzle is solved, once the previous best time is known. */
    val solveSummary: SolveSummary? = null,
)

data class SolveSummary(
    val previousBestSeconds: Long?,
)

class GameViewModel(
    private val puzzleRepository: PuzzleRepository,
    private val gameRecordRepository: GameRecordRepository,
    private val resources: Resources,
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private var loadedFor: Pair<Int, PuzzleLevel>? = null
    private var timerJob: Job? = null

    private var isScreenStarted = true

    fun loadGame(puzzleId: Int, level: PuzzleLevel) {
        if (loadedFor == puzzleId to level) return
        loadedFor = puzzleId to level

        val puzzle = puzzleRepository.findById(puzzleId) ?: return

        _uiState.value = GameUiState(isLoading = true, puzzle = puzzle)
        viewModelScope.launch {
            val (fullImage, pieces) = withContext(ioDispatcher) {
                val bitmap = loadPuzzleBitmap(resources, puzzle, FULL_SIZE_MAX_DIMENSION)
                val pieces =
                    sliceImageIntoPieces(bitmap, rows = level.gridSize, cols = level.gridSize)
                bitmap.asImageBitmap() to pieces
            }
            val pieceAspect = pieces.first().width.toFloat() / pieces.first().height.toFloat()

            _uiState.value = GameUiState(
                isLoading = false,
                puzzle = puzzle,
                cols = level.gridSize,
                pieceAspect = pieceAspect,
                pieces = pieces,
                fullImage = fullImage,
                order = shuffledOrder(pieces.size),
            )
            startTimer()
        }
    }

    fun restart() {
        val state = _uiState.value
        if (state.pieces.isEmpty()) return

        _uiState.value = state.copy(
            order = shuffledOrder(state.pieces.size),
            selectedCell = null,
            isSolved = false,
            elapsedSeconds = 0,
            solveSummary = null,
        )
        startTimer()
    }

    fun onScreenStarted() {
        isScreenStarted = true

        val state = _uiState.value
        val isPlaying = !state.isLoading && !state.isSolved
        if (isPlaying && timerJob?.isActive != true) {
            startTimer()
        }
    }

    fun onScreenStopped() {
        isScreenStarted = false
        timerJob?.cancel()
        timerJob = null
    }

    private fun startTimer() {
        timerJob?.cancel()
        if (!isScreenStarted) return

        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000.milliseconds)
                _uiState.value =
                    _uiState.value.copy(elapsedSeconds = _uiState.value.elapsedSeconds + 1)
            }
        }
    }

    fun onCellClicked(cellIndex: Int) {
        val state = _uiState.value
        if (state.isSolved) return

        val selected = state.selectedCell
        if (selected == null) {
            _uiState.value = state.copy(selectedCell = cellIndex)
            return
        }

        if (selected == cellIndex) {
            _uiState.value = state.copy(selectedCell = null)
            return
        }

        val newOrder = state.order.toMutableList().also { list ->
            val tmp = list[selected]
            list[selected] = list[cellIndex]
            list[cellIndex] = tmp
        }
        val isSolved = newOrder == newOrder.indices.toList()
        if (isSolved) {
            timerJob?.cancel()
            finishGame(completionTime = state.elapsedSeconds)
        }

        _uiState.value = state.copy(
            order = newOrder,
            selectedCell = null,
            isSolved = isSolved
        )
    }

    private fun finishGame(completionTime: Int) {
        val (puzzleId, level) = loadedFor ?: return
        viewModelScope.launch {
            val previousBest = runCatching {
                gameRecordRepository.getBestTime(
                    puzzleId,
                    level.name
                )
            }.getOrNull()

            runCatching {
                gameRecordRepository.save(
                    GameRecord(
                        puzzleId = puzzleId,
                        level = level.name,
                        completionTime = completionTime.toLong(),
                        timestamp = LocalDateTime.now(),
                    )
                )
            }

            _uiState.value = _uiState.value.copy(solveSummary = SolveSummary(previousBest))
        }
    }
}
