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

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    )

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
