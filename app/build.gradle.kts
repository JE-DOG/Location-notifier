import ru.je_dog.location_notifier.convention.core.ext.implementationProject

plugins {
    id("je_dog.android.application")
    id("je_dog.android.compose")
}

android {
    namespace = "ru.je_dog.location_notifier"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementationProject(":feature:location-list")
    implementationProject(":feature:set-geo-point")

}