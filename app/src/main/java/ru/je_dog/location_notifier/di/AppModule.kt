package ru.je_dog.location_notifier.di

import androidx.core.os.BuildCompat
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.je_dog.core.app.AppBuildConfig
import ru.je_dog.core.feature.app.tool_bar.ToolBarManager
import ru.je_dog.core.feature.app.vm.MainViewModel
import ru.je_dog.core.feature.app.vm.MainViewState
import ru.je_dog.location_notifier.BuildConfig
import ru.je_dog.location_notifier.vm.MainViewModelImpl
import ru.je_dog.location_notifier.vm.reducer.MainMutation
import ru.je_dog.location_notifier.vm.reducer.MainReducer

@Module(
    includes = [
        AppModuleBind::class
    ]
)
class AppModule {
    @Provides
    fun provideMainViewModel(): MainViewModel{
        return MainViewModelImpl()
    }

    @Provides
    fun provideAppBuildConfig(): AppBuildConfig {
        return object: AppBuildConfig {
            override val isDebug: Boolean = BuildConfig.DEBUG
        }
    }

}

@Module
interface AppModuleBind {

    @Binds
    fun bindToolBarManager(
        mainViewModel: MainViewModel
    ): ToolBarManager

}