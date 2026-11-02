package simostudio96.speengriddy.hubapp.ui.composable.screen.game_records.card

data class GameRecordCardItem(
    /** 1-based place among the games of the same difficulty, fastest first. */
    val rank: Int,
    val puzzleTitle: String,
    val formattedTime: String,
    val formattedDate: String,
)
