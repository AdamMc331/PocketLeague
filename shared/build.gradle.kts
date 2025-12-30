import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType
import org.jmailen.gradle.kotlinter.tasks.FormatTask
import org.jmailen.gradle.kotlinter.tasks.LintTask

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = false
        }
    }

    jvm("desktop")

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.androidx.navigation3.ui)
            implementation(libs.coil.compose)
            implementation(libs.coil.ktor)
            implementation(libs.compose.material3.adaptive)
            implementation(libs.koin.compose.viewmodel.navigation)
            implementation(libs.koin.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)
        }

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.android)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.google.testparameterinjector)
            implementation(libs.koin.test)
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
                                    "=com.adammcneilly.pocketleague.shared.Parcelize",
                            )
                        }
                    }
                }
            }
        }
    }
}

compose.resources {
    publicResClass = false
    packageOfResClass = "com.adammcneilly.pocketleague.shared"
    generateResClass = auto
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    namespace = "com.adammcneilly.pocketleague.shared"
}

tasks.withType<FormatTask> {
    exclude { it.file.path.contains("build/")}
}

tasks.withType<LintTask> {
    exclude { it.file.path.contains("build/")}
}
