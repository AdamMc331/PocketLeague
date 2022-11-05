buildscript {
    repositories {
        google()
    }
}

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    // Using Chris Banes fork until fix is merged into Paparazzi
    // https://github.com/cashapp/paparazzi/pull/605
    id("dev.chrisbanes.paparazzi").version("1.1.0-sdk33-alpha02")
}

android {
    namespace = "com.adammcneilly.pocketleague.android.designsystem"
    compileSdk = AndroidConfig.compileSDK

    defaultConfig {
        minSdk = AndroidConfig.minSDK
        targetSdk = AndroidConfig.targetSDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
}

dependencies {
    implementation(project(":core:displaymodels"))
    implementation(libs.bundles.compose.ui)
    implementation("com.google.accompanist:accompanist-placeholder-material:${Versions.accompanist}")
    implementation("io.coil-kt:coil-compose:2.2.1")
    testImplementation(project(":core:displaymodels-test"))
    testImplementation("com.google.testparameterinjector:test-parameter-injector:1.9")
    androidTestImplementation(project(":core:displaymodels-test"))
    androidTestImplementation(libs.bundles.androidx.test)
    androidTestImplementation(libs.compose.ui.test.junit)
    debugImplementation(libs.bundles.compose.ui.debug)
}
