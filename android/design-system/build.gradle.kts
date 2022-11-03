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
    implementation("androidx.compose.ui:ui:${Versions.compose}")
    implementation("androidx.compose.ui:ui-util:${Versions.compose}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.compose}")
    implementation("androidx.compose.material:material-icons-extended:${Versions.compose}")
    implementation("androidx.compose.material3:material3:${Versions.composeMaterial3}")
    implementation("com.google.accompanist:accompanist-placeholder-material:${Versions.accompanist}")
    implementation(libs.coil.compose)
    testImplementation(project(":core:displaymodels-test"))
    testImplementation("com.google.testparameterinjector:test-parameter-injector:1.9")
    androidTestImplementation(project(":core:displaymodels-test"))
    androidTestImplementation("androidx.test.ext:junit:${Versions.androidxTest}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espresso}")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.compose}")
    debugImplementation("androidx.compose.ui:ui-tooling:${Versions.compose}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${Versions.compose}")
}
