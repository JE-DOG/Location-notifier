package ru.je_dog.location_notifier.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.je_dog.feature.location_list.di.deps.LocationListComponentDeps
import ru.je_dog.feature.notification_settings.di.deps.NotificationSettingsComponentDeps
import ru.je_dog.location_notifier.MainActivity
import ru.je_dog.set_geo_point.di.deps.SetGeoPointComponentDeps

@Component(
    modules = [
        CommonModule::class
    ]
)
interface AppComponent:
    LocationListComponentDeps,
    SetGeoPointComponentDeps,
    NotificationSettingsComponentDeps {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            context: Context
        ): AppComponent
    }
}