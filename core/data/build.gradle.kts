plugins {
    id("je_dog.android.library")
}

android {
    namespace = "ru.je_dog.core.data"
}

dependencies {

    implementation( project(":core") )
    implementation( project(":core:domain") )

}