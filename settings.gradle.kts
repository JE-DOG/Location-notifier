pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "LocationNotifier"
include(":app")
include(":core")
include(":core:domain")
include(":core:data")
include(":core:feature")
include(":domain")
include(":domain:location-list")
include(":data")
include(":data:location-list")
