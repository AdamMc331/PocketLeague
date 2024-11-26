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
                implementation(projects.core.datetime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

    }
}
