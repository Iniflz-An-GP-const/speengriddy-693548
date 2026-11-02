package simostudio96.speengriddy.hubapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import simostudio96.speengriddy.hubapp.data.entity.GameRecord

@Dao
interface GameRecordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(score: GameRecord)

    @Query("SELECT * FROM game_records ORDER BY timestamp DESC")
    suspend fun getAll(): List<GameRecord>

    @Query("SELECT MIN(completion_time) FROM game_records WHERE puzzle_id = :puzzleId AND level = :level")
    suspend fun getBestTime(puzzleId: Int, level: String): Long?

    @Query("DELETE FROM game_records")
    suspend fun deleteAll()
}