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
                implementation(libs.cketti.codepoints)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.varabyte.truthish)
            }
        }
    }
}
