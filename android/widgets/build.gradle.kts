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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
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

    implementation(project(":core:displaymodels"))
    implementation(project(":core:models"))
    implementation(project(":data:local-sqldelight"))
    implementation(project(":data:match"))
    implementation(project(":data:octanegg"))
    implementation(project(":data:remote"))
    implementation(libs.androidx.workmanager.ktx)
    implementation(libs.bundles.androidx.glance)
    implementation(libs.google.material)
    implementation(libs.kotlinx.coroutines.core)
}
