plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.parcelize")
    alias(libs.plugins.kotlin.compose)
}

kotlin {
    androidTarget()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.datetime)
                implementation(projects.core.displaymodels)
                implementation(projects.core.feature)
                implementation(projects.core.models)
                implementation(projects.data.event)
                implementation(projects.data.game)
                implementation(projects.data.localSqldelight)
                implementation(projects.data.match.api)
                implementation(projects.data.match.impl)
                implementation(projects.data.octanegg)
                implementation(projects.data.player)
                implementation(projects.data.remote)
                implementation(projects.data.team)
                implementation(projects.feature.eventdetail)
                implementation(projects.feature.teamdetail)
                implementation(projects.shared.designSystem)
                implementation(projects.shared.ui)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(libs.koin.core)
                implementation(libs.slack.circuit)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        // val androidTest by getting
        maybeCreate("iosX64Main")
        maybeCreate("iosArm64Main")
        maybeCreate("iosSimulatorArm64Main")
        maybeCreate("iosMain").apply {
            dependsOn(commonMain)
            getAt("iosX64Main").dependsOn(this)
            getAt("iosArm64Main").dependsOn(this)
            getAt("iosSimulatorArm64Main").dependsOn(this)
        }
        maybeCreate("iosX64Test")
        maybeCreate("iosArm64Test")
        maybeCreate("iosSimulatorArm64Test")
        maybeCreate("iosTest").apply {
            dependsOn(commonTest)
            getAt("iosX64Test").dependsOn(this)
            getAt("iosArm64Test").dependsOn(this)
            getAt("iosSimulatorArm64Test").dependsOn(this)
        }
    }
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    namespace = "com.adammcneilly.pocketleague.shared.app"
}

project.extensions.findByType(org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension::class.java)
    ?.apply {
        if (project.findProperty("ios") == "true") {
            iosX64()
            iosArm64()
            iosSimulatorArm64()

            cocoapods {
                version = "1.0.0"
                summary = "Some description for the Shared Module"
                homepage = "Link to the Shared Module homepage"
                ios.deploymentTarget = "14.1"
                podfile = project.file("../../pocketLeagueIos/Podfile")
                framework {
                    baseName = "shared"
                    isStatic = true
                }
            }
        }
        if (project.findProperty("js") == "true") {
            js(IR) {
                browser()
            }
        }
    }
