plugins {
    id("je_dog.data")
}

android {
    namespace = "ru.je_dog.core.data.location_notification"
}

dependencies {
    with(projects){
        implementation(domain.locationNotificattion)
    }
}