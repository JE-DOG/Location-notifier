plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(17)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {

    plugins {

        register("je_dogAndroidCompose"){
            id = "je_dog.android.compose"
            implementationClass = "ComposeConventionPlugin"
        }

        register("je_dogAndroidApplication"){
            id = "je_dog.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("je_dogAndroidLibrary"){
            id = "je_dog.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("je_dogKotlinLibrary"){
            id = "je_dog.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }

        register("je_dogDomain"){
            id = "je_dog.domain"
            implementationClass = "DomainConventionPlugin"
        }

        register("je_dogData"){
            id = "je_dog.data"
            implementationClass = "DataConventionPlugin"
        }

        register("je_dogFeature"){
            id = "je_dog.feature"
            implementationClass = "FeatureConventionPlugin"
        }

    }

}