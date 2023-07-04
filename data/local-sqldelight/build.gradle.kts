plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:models"))
                implementation(project(":core:models-test"))
                implementation(libs.square.sqldelight.coroutines)
                implementation(libs.square.sqldelight.runtime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(project(":core:models-test"))
                implementation(libs.cash.turbine)
                implementation(libs.varabyte.truthish)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.square.sqldelight.android.driver)
                implementation("com.github.requery:sqlite-android:3.39.2")
            }
        }
        // val androidTest by getting
        maybeCreate("iosX64Main")
        maybeCreate("iosArm64Main")
        maybeCreate("iosSimulatorArm64Main")
        maybeCreate("iosMain").apply {
            dependsOn(commonMain)
            getAt("iosX64Main").dependsOn(this)
            getAt("iosArm64Main").dependsOn(this)
            getAt("iosSimulatorArm64Main").dependsOn(this)

            dependencies {
                implementation(libs.square.sqldelight.native.driver)
            }
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

    namespace = "com.adammcneilly.pocketleague.data.local.sqldelight"
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

sqldelight {
    database("PocketLeagueDB") {
        packageName = "com.adammcneilly.pocketleague.data.local.sqldelight"
        dialect = "sqlite:3.24"
    }
}

tasks.formatKotlinCommonMain {
    exclude { it.file.path.contains("build/")}
}

tasks.lintKotlinCommonMain {
    exclude { it.file.path.contains("build/")}
}
