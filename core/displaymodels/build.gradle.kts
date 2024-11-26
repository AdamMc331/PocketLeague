plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    )

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.currency)
                implementation(projects.core.datetime)
                implementation(projects.core.locale)
                implementation(projects.core.models)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(projects.core.datetimeTest)
                implementation(projects.core.displaymodelsTest)
                implementation(projects.core.modelsTest)
                implementation(libs.varabyte.truthish)
            }
        }

    }
}
