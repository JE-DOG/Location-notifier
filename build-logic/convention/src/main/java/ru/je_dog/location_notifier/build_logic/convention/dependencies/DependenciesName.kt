package ru.je_dog.location_notifier.build_logic.convention.dependencies

object DependenciesName {

//AndroidX
    const val androidxActivityCompose = "androidx.activity.compose"
    const val androidxCoreKtx = "androidx.core.ktx"
    const val androidxLifecycleRuntimeKtx = "androidx.core.ktx"
    const val androidxLifecycleRuntimeCompose = "androidx.lifecycle.runtime.compose"

//Compose

    const val compose_navigation = "compose.navigation"

    const val composeBom = "compose.bom"
    const val composeUi = "compose.ui"
    const val composeUiGraphics = "compose.ui.graphics"
    const val composeUiToolingPreview = "compose.ui.tooling.preview"
    const val composeMaterial3 = "compose.material3"
    const val composeMaterial = "compose.material"
    //Tests
    const val composeJunitUiTest = "compose.junit.ui.test"
    const val composeJunitUiTooling = "compose.junit.ui.tooling"
    const val composeJunitUiTestManifest = "compose.junit.ui.test.manifest"
    const val extensionAndroidx = "extension.androidx"

//DI

    const val dagger_core = "dagger.core"
    const val dagger_android = "dagger.android"
    const val dagger_compiler = "dagger.compiler"

//Room

    const val room = "room"
    const val room_ktx = "room.ktx"
    const val room_compiler = "room.compiler"

//Tests

    //Junit
    const val jUnit = "junit"
    const val jUnitExtensionAndroidx = "junit.extension.androidx"
    //Espresso
    const val  espressoCore = "espresso.core"

//Other

    //Coroutines
    const val coroutines_core = "kotlin.coroutines.core"

}