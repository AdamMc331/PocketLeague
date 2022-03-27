import java.io.FileInputStream
import java.util.Properties

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.apollographql.apollo3").version(Versions.apollo)
    id("com.codingfeline.buildkonfig").version(Versions.buildKonfig)
}

kotlin {
    android()

    listOf(
        iosX64(),
        iosArm64(),
        // iosSimulatorArm64() sure all ios dependencies support this target
    ).forEach {
        it.binaries.framework {
            baseName = "event-implementation"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":shared"))
                implementation("com.apollographql.apollo3:apollo-runtime:${Versions.apollo}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation("app.cash.turbine:turbine:${Versions.turbine}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}")
            }
        }
        val androidMain by getting
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        // val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            // iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        // val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            // iosSimulatorArm64Test.dependsOn(this)
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

apollo {
    packageName.set("com.adammcneilly.pocketleague.event.graphql")
}

buildkonfig {
    val secretsFile = File("event-implementation/local.properties")
    val properties = Properties()

    if (secretsFile.exists()) {
        properties.load(FileInputStream(secretsFile))
    }

    defaultConfigs {
        packageName = "com.adammcneilly.pocketleague.event.implementation"

        buildConfigField(
            type = com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            name = "SMASH_GG_API_KEY",
            value = properties["SmashGGAPIKey"].toString(),
        )
    }
}
