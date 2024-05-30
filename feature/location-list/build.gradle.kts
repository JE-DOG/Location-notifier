plugins{
    id("je_dog.feature")
}

android {
    namespace = "ru.je_dog.feature.location_list"
}

dependencies {
    with(projects){
        implementation(domain.locationList)
        implementation(data.locationList)
    }

    with(libs){
        implementation(gms.location)
    }
}