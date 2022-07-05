plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply(plugin = "app.cash.paparazzi")

android {
    compileSdk = AndroidConfig.compileSDK

    defaultConfig {
        minSdk = AndroidConfig.minSDK
        targetSdk = AndroidConfig.targetSDK
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {

    testImplementation("androidx.compose.material3:material3:1.0.0-alpha12")
    testImplementation("androidx.compose.ui:ui:${Versions.compose}")
    testImplementation(project(":composables"))
}
