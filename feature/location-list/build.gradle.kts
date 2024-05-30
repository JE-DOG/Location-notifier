plugins{
    id("je_dog.feature")
}

android {
    namespace = "ru.je_dog.feature.location_list"
}

dependencies {
    with(projects){
        with(domain){
            implementation(locationList)
            implementation(locationNotificattion)
        }
        with(data){
            implementation(locationList)
            implementation(locationNotification)
        }
    }

    with(libs){
        implementation(gms.location)
    }
}