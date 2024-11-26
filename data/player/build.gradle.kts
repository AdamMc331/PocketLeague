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
                implementation(projects.core.models)
                implementation(projects.data.localSqldelight)
                implementation(projects.data.octanegg)
                implementation(projects.data.remote)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.square.sqldelight.coroutines)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        
    }
}
