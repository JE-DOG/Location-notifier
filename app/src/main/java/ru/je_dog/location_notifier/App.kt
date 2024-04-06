package ru.je_dog.location_notifier

import android.app.Application
import ru.je_dog.feature.location_list.di.deps.LocationListComponentDepsStore
import ru.je_dog.location_notifier.di.DaggerAppComponent

class App: Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory()
            .create(
                this
            )
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        initDI()
    }

    private fun initDI() {
        with(appComponent){
            LocationListComponentDepsStore.deps = this
        }
    }

    companion object {

        lateinit var INSTANCE: App
            private set

    }
}