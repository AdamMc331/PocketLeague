plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

kotlin {
    androidTarget()
    jvm()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    )

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.models)
                implementation(projects.core.modelsTest)
                implementation(libs.square.sqldelight.coroutines)
                implementation(libs.square.sqldelight.runtime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(projects.core.modelsTest)
                implementation(libs.cash.turbine)
                implementation(libs.varabyte.truthish)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.square.sqldelight.android.driver)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.square.sqldelight.sqlite.driver)
            }
        }
        iosMain.dependencies {
            implementation(libs.square.sqldelight.native.driver)
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
