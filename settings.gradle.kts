pluginManagement {
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

rootProject.name = "PocketLeague"

plugins {
    id("org.jetbrains.kotlinx.kover.aggregation").version("0.9.2")
}

kover {
    enableCoverage()
}

include(":androidApp")
include(":desktopApp")
include(":shared")
