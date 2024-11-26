plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.models)
                implementation(projects.data.localSqldelight)
                implementation(projects.data.event.api)
                implementation(projects.data.octanegg)
                implementation(projects.data.remote)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.mobilenativefoundation.store)
                implementation(libs.square.sqldelight.coroutines)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(projects.core.modelsTest)
                implementation(projects.core.test)
//                implementation(projects.data.event.test)
                implementation(projects.data.remoteTest)
                implementation(libs.cash.turbine)
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.varabyte.truthish)
            }
        }
    }
}
