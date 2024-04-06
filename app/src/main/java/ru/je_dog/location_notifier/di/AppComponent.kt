package ru.je_dog.location_notifier.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.je_dog.feature.location_list.di.deps.LocationListComponentDeps
import ru.je_dog.location_notifier.MainActivity

@Component(
    modules = [
        CoreModule::class
    ]
)
interface AppComponent: LocationListComponentDeps {
    fun inject(mainActivity: MainActivity)

    override val context: Context

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance
            context: Context
        ): AppComponent

    }

}