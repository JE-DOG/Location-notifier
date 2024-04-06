plugins {
    id("je_dog.android.library")
    id("je_dog.android.compose")
    id("kotlin-parcelize")
}

android {
    namespace = "ru.je_dog.core.feature"
}

dependencies {

    implementation( project(":core") )
    implementation( project(":core:domain") )

    with(libs){

        implementation(gms.location)
        implementation(osmdroid)

    }

}