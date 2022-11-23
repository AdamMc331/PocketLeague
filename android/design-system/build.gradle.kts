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
    id("shot")
}

android {
    namespace = "com.adammcneilly.pocketleague.android.designsystem"
    compileSdk = AndroidConfig.compileSDK

    defaultConfig {
        minSdk = AndroidConfig.minSDK
        targetSdk = AndroidConfig.targetSDK

        testApplicationId = "com.adammcneilly.pocketleague.android.designsystem.test"
        testInstrumentationRunner = "com.karumi.shot.ShotTestRunner"
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
    androidTestImplementation(project(":core:displaymodels-test"))
    androidTestImplementation(libs.bundles.androidx.test)
    androidTestImplementation(libs.compose.ui.test.junit)

    debugImplementation(libs.bundles.compose.ui.debug)

    implementation(project(":core:displaymodels"))
    implementation(libs.accompanist.placeholder.material)
    implementation(libs.bundles.compose.ui)
    implementation(libs.coil.compose)

    testImplementation(project(":core:displaymodels-test"))
    testImplementation(libs.google.testparameterinjector)
}
