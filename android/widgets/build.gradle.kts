plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.adammcneilly.pocketleague.widgets"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.compileSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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

    implementation(projects.core.datetime)
    implementation(projects.core.displaymodels)
    implementation(projects.core.models)
    implementation(projects.data.localSqldelight)
    implementation(projects.data.match)
    implementation(projects.data.octanegg)
    implementation(projects.data.remote)
    implementation(libs.androidx.workmanager.ktx)
    implementation(libs.bundles.androidx.glance)
    implementation(libs.google.material)
    implementation(libs.kotlinx.coroutines.core)
}
