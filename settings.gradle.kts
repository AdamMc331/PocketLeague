pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://central.sonatype.com/repository/maven-snapshots/")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://central.sonatype.com/repository/maven-snapshots/")
    }
}

rootProject.name = "PocketLeague"

plugins {
    id("org.jetbrains.kotlinx.kover.aggregation").version("0.9.4")
}

kover {
    enableCoverage()
}

include(":androidApp")
include(":desktopApp")
include(":shared")
include(":test:paparazzi")
