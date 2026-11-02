package simostudio96.speengriddy.hubapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import simostudio96.speengriddy.hubapp.data.dao.GameRecordDao
import simostudio96.speengriddy.hubapp.data.database.converter.Converters
import simostudio96.speengriddy.hubapp.data.entity.GameRecord

@Database(
    entities = [GameRecord::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class SUEDPAppDatabase : RoomDatabase() {

    abstract fun gameRecordDao(): GameRecordDao
}