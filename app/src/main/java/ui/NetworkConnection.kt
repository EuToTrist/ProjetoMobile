package ui

import android.app.Application
import di.networkModule
import di.repositoryModules
import di.viewModelModules
import org.koin.core.context.startKoin

class NetworkConnection: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                listOf(
                    repositoryModules,
                    networkModule,
                    viewModelModules
                )
            )
        }
    }

}