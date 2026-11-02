package simostudio96.speengriddy.hubapp.di

import androidx.room.Room
import simostudio96.speengriddy.hubapp.data.database.SUEDPAppDatabase
import simostudio96.speengriddy.hubapp.data.repository.GameRecordRepository
import simostudio96.speengriddy.hubapp.data.repository.PuzzleRepository
import org.koin.dsl.module

private const val DB_NAME = "suedp_puzzle_db"

val dataModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = SUEDPAppDatabase::class.java,
            name = DB_NAME
        )
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }

    single { get<SUEDPAppDatabase>().gameRecordDao() }

    single {
        GameRecordRepository(
            gameRecordDao = get(),
            ioDispatcher = get(DispatchersQualifiers.IO)
        )
    }

    single { PuzzleRepository() }

}