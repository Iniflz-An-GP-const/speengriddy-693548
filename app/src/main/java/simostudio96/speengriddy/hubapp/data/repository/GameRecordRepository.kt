package simostudio96.speengriddy.hubapp.data.repository

import simostudio96.speengriddy.hubapp.data.dao.GameRecordDao
import simostudio96.speengriddy.hubapp.data.entity.GameRecord
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GameRecordRepository(
    private val gameRecordDao: GameRecordDao,
    private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun save(gameRecord: GameRecord) {
        withContext(ioDispatcher) {
            gameRecordDao.insert(gameRecord)
        }
    }

    suspend fun getAll(): List<GameRecord> {
        return withContext(ioDispatcher) {
            gameRecordDao.getAll()
        }
    }

    suspend fun getBestTime(puzzleId: Int, level: String): Long? {
        return withContext(ioDispatcher) {
            gameRecordDao.getBestTime(puzzleId, level)
        }
    }

    suspend fun deleteAll() {
        withContext(ioDispatcher) {
            gameRecordDao.deleteAll()
        }
    }
}
