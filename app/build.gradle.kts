plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp").version("${Versions.kotlin}-1.0.6")
}

apply(from = "../buildscripts/jacoco.gradle")
apply(from = "../buildscripts/jacocoCoverage.gradle")
apply(from = "../buildscripts/coveralls.gradle")

kotlin {
    sourceSets {
        debug {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }
        release {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
    }
}

android {
    compileSdk = AndroidConfig.compileSDK

    defaultConfig {
        applicationId = "com.adammcneilly.pocketleague"
        minSdk = AndroidConfig.minSDK
        targetSdk = AndroidConfig.targetSDK
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "com.adammcneilly.pocketleague.PocketLeagueTestRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
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

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.adammcneilly.pocketleague"
}

dependencies {

    implementation(project(":android:design-system"))
    implementation(project(":android:widgets"))
    implementation(project(":shared"))
    implementation(project(":core:models"))
    implementation(project(":core:displaymodels"))
    implementation(libs.kotlinx.datetime)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)
    implementation(libs.bundles.compose.ui)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.placeholder.material)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.androidx.window)
    implementation(libs.androidx.lifecycle.process)
    implementation(libs.coil.compose)
    implementation(libs.androidx.palette.ktx)
    testImplementation(libs.junit)
    testImplementation(libs.google.truth)
    testImplementation(libs.cash.turbine)
    androidTestImplementation(project(":shared-test"))
    androidTestImplementation(libs.bundles.androidx.test)
    androidTestImplementation(libs.compose.ui.test.junit)
    debugImplementation(libs.bundles.compose.ui.debug)
}
