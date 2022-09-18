plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
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
}

dependencies {

    implementation("androidx.compose.ui:ui:${Versions.compose}")
    implementation("androidx.compose.ui:ui-util:${Versions.compose}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.compose}")
    implementation("androidx.compose.material:material-icons-extended:${Versions.compose}")
    implementation("androidx.compose.material3:material3:1.0.0-alpha12")
}
