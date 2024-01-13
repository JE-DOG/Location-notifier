import ru.je_dog.location_notifier.convention.core.ext.implementationProject

plugins{
    id("je_dog.feature")
}

android {
    namespace = "ru.je_dog.feature.location_list"
}

dependencies {

    implementationProject(":domain:location-list")
    implementationProject(":data:location-list")

    with(libs){

        implementation(gms.location)
        implementation(osmdroid)

    }

}