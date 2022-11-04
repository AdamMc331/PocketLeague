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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    implementation(project(":shared"))
    implementation(project(":core:models"))
    implementation(project(":core:displaymodels"))
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDatetime}")
    implementation("androidx.core:core-ktx:${Versions.ktxCore}")
    implementation("androidx.appcompat:appcompat:${Versions.appCompat}")
    implementation("com.google.android.material:material:${Versions.material}")
    implementation(libs.bundles.compose.ui)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}")
    implementation("androidx.activity:activity-compose:${Versions.activityCompose}")
    implementation("com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-placeholder-material:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-flowlayout:${Versions.accompanist}")
    implementation("androidx.window:window:${Versions.windowManager}")
    implementation("androidx.lifecycle:lifecycle-process:2.5.0")
    implementation("io.coil-kt:coil-compose:2.2.1")
    implementation("androidx.palette:palette-ktx:1.0.0")
    testImplementation("junit:junit:${Versions.junit}")
    testImplementation("com.google.truth:truth:${Versions.truth}")
    testImplementation("app.cash.turbine:turbine:${Versions.turbine}")
    androidTestImplementation(project(":shared-test"))
    androidTestImplementation("androidx.test.ext:junit:${Versions.androidxTest}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espresso}")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.compose}")
    debugImplementation(libs.bundles.compose.ui.debug)
}
