import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import java.io.FileInputStream
import java.util.Properties

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.apollographql.apollo3").version(libs.versions.apollo)
    id("com.codingfeline.buildkonfig").version(libs.versions.buildkonfig)
}

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.apollo.runtime)
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
    compileSdk = libs.versions.compileSdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.compileSdk.get().toInt()
    }

    namespace = "com.adammcneilly.pocketleague.data.startgg"
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

apollo {
    service("service") {
        packageName.set("com.adammcneilly.pocketleague.data.startgg")
        generateApolloMetadata.set(true)
    }
}

buildkonfig {
    packageName = "com.adammcneilly.pocketleague.data.startgg"

    val properties = Properties()
    properties.load(FileInputStream(project.rootProject.file("local.properties")))

    defaultConfigs {
        buildConfigField(STRING, "START_GG_API_KEY", properties["START_GG_API_KEY"].toString())
    }
}

tasks.formatKotlinCommonMain {
    exclude { it.file.path.contains("generated/")}
}

tasks.lintKotlinCommonMain {
    exclude { it.file.path.contains("generated/")}
}
