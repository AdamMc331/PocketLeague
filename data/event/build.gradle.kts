plugins {
    kotlin("multiplatform")
    id("com.apollographql.apollo3").version(libs.versions.apollo)
}

dependencies {
    apolloMetadata(projects.data.startgg)
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.datetime)
                implementation(projects.core.models)
                implementation(projects.data.localSqldelight)
                implementation(projects.data.octanegg)
                implementation(projects.data.remote)
                implementation(projects.data.startgg)
                implementation(libs.apollo.runtime)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.square.sqldelight.coroutines)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(projects.core.modelsTest)
                implementation(projects.data.remoteTest)
                implementation(libs.cash.turbine)
                implementation(libs.kotlinx.coroutines.test)
            }
        }
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

tasks.formatKotlinCommonMain {
    exclude { it.file.path.contains("build/")}
}

tasks.lintKotlinCommonMain {
    exclude { it.file.path.contains("build/")}
}
