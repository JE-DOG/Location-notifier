plugins {
    id("je_dog.data")
}

android {
    namespace = "ru.je_dog.core.data.location_list"
}

dependencies {
    with(projects){
        implementation(domain.locationList)
    }
}