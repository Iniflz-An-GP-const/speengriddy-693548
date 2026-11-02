package simostudio96.speengriddy.hubapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import simostudio96.speengriddy.hubapp.data.model.Puzzle
import simostudio96.speengriddy.hubapp.data.model.PuzzleLevel
import simostudio96.speengriddy.hubapp.data.repository.GameRecordRepository
import simostudio96.speengriddy.hubapp.data.repository.PuzzleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class PuzzleSetupUiState(
    val puzzles: List<Puzzle> = emptyList(),
    val selectedPuzzleIndex: Int = 0,
    val selectedLevel: PuzzleLevel = PuzzleLevel.EASY,
    val bestTimeSeconds: Long? = null,
    val isPickerVisible: Boolean = false,
) {
    val selectedPuzzle: Puzzle? get() = puzzles.getOrNull(selectedPuzzleIndex)
}

class PuzzleSetupViewModel(
    puzzleRepository: PuzzleRepository,
    private val gameRecordRepository: GameRecordRepository,
) : ViewModel() {
    private val puzzles = puzzleRepository.getPuzzles()

    private val selectedPuzzleIndex = MutableStateFlow(0)
    private val selectedLevel = MutableStateFlow(PuzzleLevel.EASY)
    private val isPickerVisible = MutableStateFlow(false)

    private val bestTimes = MutableStateFlow<Map<Pair<Int, String>, Long>>(emptyMap())

    val uiState: StateFlow<PuzzleSetupUiState> = combine(
        selectedPuzzleIndex,
        selectedLevel,
        isPickerVisible,
        bestTimes,
    ) { index, level, pickerVisible, times ->
        PuzzleSetupUiState(
            puzzles = puzzles,
            selectedPuzzleIndex = index,
            selectedLevel = level,
            bestTimeSeconds = puzzles.getOrNull(index)?.let { times[it.id to level.name] },
            isPickerVisible = pickerVisible,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = PuzzleSetupUiState(puzzles = puzzles),
    )

    init {
        viewModelScope.launch {
            bestTimes.value = gameRecordRepository.getAll()
                .groupBy { it.puzzleId to it.level }
                .mapValues { (_, records) -> records.minOf { it.completionTime } }
        }
    }

    fun onPuzzleSelected(index: Int) {
        selectedPuzzleIndex.value = index
    }

    fun onLevelSelected(level: PuzzleLevel) {
        selectedLevel.value = level
    }

    fun onPickerVisibilityChange(visible: Boolean) {
        isPickerVisible.value = visible
    }
}
