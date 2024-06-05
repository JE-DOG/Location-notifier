plugins {
    id("je_dog.android.application")
    id("je_dog.android.compose")
}

android {
    namespace = "ru.je_dog.location_notifier"
}

dependencies {
    with(projects){
        with(feature){
            implementation(locationList)
            implementation(setGeoPoint)
            implementation(notificationSettings)
        }
    }
}