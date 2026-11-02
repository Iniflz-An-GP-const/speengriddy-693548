package simostudio96.speengriddy.hubapp.di

import android.content.Context
import simostudio96.speengriddy.hubapp.ui.viewmodel.GameRecordViewModel
import simostudio96.speengriddy.hubapp.ui.viewmodel.GameViewModel
import simostudio96.speengriddy.hubapp.ui.viewmodel.PuzzleSetupViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        PuzzleSetupViewModel(
            puzzleRepository = get(),
            gameRecordRepository = get(),
        )
    }

    viewModel {
        GameViewModel(
            puzzleRepository = get(),
            gameRecordRepository = get(),
            resources = get<Context>().resources,
            ioDispatcher = get(DispatchersQualifiers.IO),
        )
    }

    viewModel {
        GameRecordViewModel(
            puzzleRepository = get(),
            gameRecordRepository = get(),
        )
    }
}