package simostudio96.speengriddy.hubapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import simostudio96.speengriddy.hubapp.data.entity.GameRecord
import simostudio96.speengriddy.hubapp.data.model.Puzzle
import simostudio96.speengriddy.hubapp.data.model.PuzzleLevel
import simostudio96.speengriddy.hubapp.data.repository.GameRecordRepository
import simostudio96.speengriddy.hubapp.data.repository.PuzzleRepository
import simostudio96.speengriddy.hubapp.ui.state.DataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class RecordSortOption {
    TIME,
    DATE,
    PUZZLE,
}

data class RankedGameRecord(
    val rank: Int,
    val record: GameRecord,
    /** The puzzle the record points at, looked up by [GameRecord.puzzleId]. */
    val puzzle: Puzzle?,
)

class GameRecordViewModel(
    private val puzzleRepository: PuzzleRepository,
    private val gameRecordRepository: GameRecordRepository,
) : ViewModel() {
    private val allRecords = MutableStateFlow<List<GameRecord>?>(null)

    private val _selectedLevel = MutableStateFlow(PuzzleLevel.EASY)
    val selectedLevelState: StateFlow<PuzzleLevel> = _selectedLevel.asStateFlow()

    private val _selectedSort = MutableStateFlow(RecordSortOption.TIME)
    val selectedSortState: StateFlow<RecordSortOption> = _selectedSort.asStateFlow()

    val recordsState: StateFlow<DataUiState<List<RankedGameRecord>>> = combine(
        allRecords,
        _selectedLevel,
        _selectedSort,
    ) { records, level, sort ->
        if (records == null) {
            DataUiState.Initial
        } else {
            DataUiState.from(rankAndSort(records, level, sort))
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = DataUiState.Initial,
    )

    init {
        viewModelScope.launch {
            allRecords.value = gameRecordRepository.getAll()
        }
    }

    fun onLevelSelected(level: PuzzleLevel) {
        _selectedLevel.value = level
    }

    fun onSortSelected(sort: RecordSortOption) {
        _selectedSort.value = sort
    }

    private fun rankAndSort(
        records: List<GameRecord>,
        level: PuzzleLevel,
        sort: RecordSortOption,
    ): List<RankedGameRecord> {
        val ranked = records
            .filter { it.level == level.name }
            .sortedWith(compareBy<GameRecord> { it.completionTime }.thenBy { it.timestamp })
            .mapIndexed { index, record ->
                RankedGameRecord(
                    rank = index + 1,
                    record = record,
                    puzzle = puzzleRepository.findById(record.puzzleId),
                )
            }

        return when (sort) {
            RecordSortOption.TIME -> ranked
            RecordSortOption.DATE -> ranked.sortedByDescending { it.record.timestamp }
            RecordSortOption.PUZZLE -> ranked.sortedWith(
                compareBy<RankedGameRecord> { it.puzzle?.title.orEmpty() }.thenBy { it.rank },
            )
        }
    }
}
