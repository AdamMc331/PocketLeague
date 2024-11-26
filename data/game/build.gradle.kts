plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.models)
                implementation(projects.data.octanegg)
                implementation(projects.data.remote)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(projects.core.modelsTest)
                implementation(projects.core.test)
                implementation(projects.data.remoteTest)
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.square.okio)
            }
        }
    }
}
