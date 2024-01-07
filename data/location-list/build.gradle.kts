import ru.je_dog.location_notifier.convention.core.ext.implementationProject

plugins {
    id("je_dog.data")
}

android {
    namespace = "ru.je_dog.core.data.location_list"
}

dependencies {

    implementationProject(":domain:location-list")

}