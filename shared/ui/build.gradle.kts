plugins {
    kotlin("multiplatform")
    id("com.android.library")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.cash.paparazzi)
}

kotlin {
    android()
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.displaymodels)
                implementation(projects.core.models)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.runtime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(projects.core.displaymodelsTest)
                implementation(kotlin("test"))
                implementation(libs.google.testparameterinjector)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.coil.compose)
            }
        }
        val jvmMain by getting
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

    namespace = "com.adammcneilly.pocketleague.shared.ui"
}

project.extensions.findByType(org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension::class.java)
    ?.apply {
        if (project.findProperty("ios") == "true") {
            listOf(
                iosX64(),
                iosArm64(),
                iosSimulatorArm64()
            ).forEach {
                it.binaries.framework {
                    baseName = project.name
                }
            }
        }
        if (project.findProperty("js") == "true") {
            js(IR) {
                browser()
            }
        }
    }
