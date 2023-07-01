buildscript {
    repositories {
        google()
    }
}

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("app.cash.paparazzi").version("1.2.0")
    id("shot")
}

android {
    namespace = "com.adammcneilly.pocketleague.android.designsystem"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.compileSdk.get().toInt()

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

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
}

dependencies {
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    debugImplementation(composeBom)
    androidTestImplementation(composeBom)

    androidTestImplementation(project(":core:displaymodels-test"))
    androidTestImplementation(libs.bundles.androidx.test)
    androidTestImplementation(libs.compose.bom)
    androidTestImplementation(libs.compose.ui.test.junit)

    debugImplementation(libs.bundles.compose.ui.debug)

    api(project(":shared:design-system"))
    implementation(project(":core:displaymodels"))
    implementation(libs.accompanist.placeholder.material)
    implementation(libs.bundles.compose.ui)
    implementation(libs.coil.compose)

    testImplementation(project(":core:displaymodels-test"))
    testImplementation(libs.google.testparameterinjector)
}
