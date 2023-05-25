plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.apollographql.apollo3").version(libs.versions.apollo)
}

dependencies {
    apolloMetadata(project(":data:startgg"))
}

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:datetime"))
                implementation(project(":core:models"))
                implementation(project(":data:local-sqldelight"))
                implementation(project(":data:octanegg"))
                implementation(project(":data:remote"))
                implementation(project(":data:startgg"))
                implementation(libs.apollo.runtime)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.square.sqldelight.coroutines)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(project(":core:models-test"))
                implementation(project(":data:remote-test"))
                implementation(libs.cash.turbine)
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.square.sqldelight.sqlite.driver)
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
    compileSdk = libs.versions.compileSdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.compileSdk.get().toInt()
    }

    namespace = "com.adammcneilly.pocketleague.data.event"
}

project.extensions.findByType(org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension::class.java)
    ?.apply {
        if (project.findProperty("ios") == "true") {
            listOf(
                iosX64(),
                iosArm64(),
                iosSimulatorArm64(),
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

apollo {
    service("service") {
        packageName.set("com.adammcneilly.pocketleague.data.event")
    }
}
