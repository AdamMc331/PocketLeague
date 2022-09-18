plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:currency"))
                implementation(project(":core:models"))
                implementation(project(":core:datetime"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(project(":core:datetime-test"))
                implementation(project(":core:models-test"))
            }
        }
    }
}
