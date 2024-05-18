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
//Core
include(":core")
include(":core:domain")
include(":core:data")
include(":core:feature")
//Domain
include(":domain")
include(":domain:location-list")
include(":domain:location-notificattion")
//Data
include(":data")
include(":data:location-list")
//Feature
include(":feature")
include(":feature:location-list")
include(":feature:set-geo-point")
