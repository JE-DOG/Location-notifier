plugins{
    id("je_dog.feature")
}

android {
    namespace = "ru.je_dog.feature.notification_settings"
}

dependencies {
    with(projects){
        implementation(domain.locationNotificattion)
        implementation(data.locationNotification)
    }

    with(libs){
        implementation(gms.location)
    }
}