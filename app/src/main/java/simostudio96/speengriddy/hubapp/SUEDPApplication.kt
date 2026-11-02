package simostudio96.speengriddy.hubapp

import android.app.Application
import simostudio96.speengriddy.hubapp.di.dataModule
import simostudio96.speengriddy.hubapp.di.dispatcherModule
import simostudio96.speengriddy.hubapp.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class SUEDPApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModules = dispatcherModule + dataModule + viewModule

        startKoin {
            androidLogger()
            androidContext(this@SUEDPApplication)
            modules(appModules)
        }
    }
}