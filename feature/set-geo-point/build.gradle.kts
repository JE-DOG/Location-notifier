plugins {
    id("je_dog.feature")
}

android {
    namespace = "ru.je_dog.set_geo_point"
}

dependencies {
    implementation(libs.osmdroid)
}