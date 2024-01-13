import ru.je_dog.location_notifier.convention.core.ext.implementationProject

plugins {
    id("je_dog.android.application")
    id("je_dog.android.compose")
}

android {
    namespace = "ru.je_dog.location_notifier"
}

dependencies {

    implementationProject(":feature:location-list")

}