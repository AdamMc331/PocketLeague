plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
}

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlin:kotlin-stdlib-common")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidTest by getting
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
    compileSdk = AndroidConfig.compileSDK
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = AndroidConfig.minSDK
        targetSdk = AndroidConfig.targetSDK
    }
}

project.extensions.findByType(org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension::class.java)?.apply {
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
}
