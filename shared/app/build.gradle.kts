import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.parcelize")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.compose)
}

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = false
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.datetime)
            implementation(projects.core.displaymodels)
            implementation(projects.core.feature)
            implementation(projects.core.models)
            implementation(projects.data.event.api)
            implementation(projects.data.event.impl)
            implementation(projects.data.game)
            implementation(projects.data.localSqldelight)
            implementation(projects.data.match.api)
            implementation(projects.data.match.impl)
            implementation(projects.data.octanegg)
            implementation(projects.data.player)
            implementation(projects.data.remote)
            implementation(projects.data.team)
            implementation(projects.feature.debugmenu)
            implementation(projects.feature.eventdetail)
            implementation(projects.feature.teamdetail)
            implementation(projects.shared.ui)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.koin.core)
            implementation(libs.slack.circuit)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }

    targets.configureEach {
        val isAndroidTarget = platformType == KotlinPlatformType.androidJvm
        compilations.configureEach {
            compileTaskProvider.configure {
                compilerOptions {
                    if (isAndroidTarget) {
                        freeCompilerArgs.addAll(
                            "-P",
                            "plugin:org.jetbrains.kotlin.parcelize:additionalAnnotation" +
                                "=com.adammcneilly.pocketleague.core.feature.Parcelize",
                        )
                    }
                }
            }
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

