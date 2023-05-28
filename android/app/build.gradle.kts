plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp").version("1.8.10-1.0.9")
    alias(libs.plugins.androidx.baselineprofile)
}

//apply(from = "../../buildscripts/jacoco.gradle")
//apply(from = "../../buildscripts/jacocoCoverage.gradle")
apply(from = "../../buildscripts/coveralls.gradle")

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

    compileSdk = 33
    defaultConfig {
        applicationId = "com.adammcneilly.pocketleague"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.compileSdk.get().toInt()
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
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    namespace = "com.adammcneilly.pocketleague"

    testOptions {
        managedDevices {
            devices {
                maybeCreate<com.android.build.api.dsl.ManagedVirtualDevice>("pixel5api30").apply {
                    device = "Pixel 5"
                    apiLevel = 30
                    systemImageSource = "aosp-atd"
                }

                maybeCreate<com.android.build.api.dsl.ManagedVirtualDevice>("pixelCapi30").apply {
                    device = "Pixel C"
                    apiLevel = 30
                    systemImageSource = "aosp-atd"
                }

                groups {
                    maybeCreate("pixelPhoneAndTablet").apply {
                        targetDevices.add(devices["pixel5api30"])
                        targetDevices.add(devices["pixelCapi30"])
                    }
                }
            }
        }
    }
}

dependencies {
    implementation(libs.profileinstaller)
    "baselineProfile"(project(mapOf("path" to ":android:baselineprofile")))
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    debugImplementation(composeBom)
    androidTestImplementation(composeBom)

    androidTestImplementation(project(":core:displaymodels-test"))
    androidTestImplementation(project(":shared-test"))
    androidTestImplementation(libs.bundles.androidx.test)
    androidTestImplementation(libs.compose.bom)
    androidTestImplementation(libs.compose.ui.test.junit)

    debugImplementation(libs.bundles.compose.ui.debug)

    implementation(project(":android:design-system"))
    implementation(project(":android:widgets"))
    implementation(project(":core:displaymodels"))
    implementation(project(":core:feature"))
    implementation(project(":core:models"))
    implementation(project(":data:local-sqldelight"))
    implementation(project(":data:event"))
    implementation(project(":data:match"))
    implementation(project(":feature:event-detail"))
    implementation(project(":feature:feed"))
    implementation(project(":shared"))
    implementation(libs.accompanist.flowlayout)
    implementation(libs.accompanist.placeholder.material)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.process)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.palette.ktx)
    implementation(libs.androidx.window)
    implementation(libs.bundles.compose.ui)
    implementation(libs.coil.compose)
    implementation(libs.kotlinx.datetime)
    implementation(libs.google.material)

    testImplementation(project(":core:models-test"))
    testImplementation(project(":data:event-test"))
    testImplementation(project(":data:match-test"))
    testImplementation(libs.cash.turbine)
    testImplementation(libs.google.truth)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}
