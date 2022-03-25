import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.apollographql.apollo3").version("3.1.0")
    id("com.google.devtools.ksp").version("1.6.10-1.0.4")
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

    val properties = Properties()
    properties.load(FileInputStream(project.rootProject.file("local.properties")))

    defaultConfig {
        applicationId = "com.adammcneilly.pocketleague"
        minSdk = AndroidConfig.minSDK
        targetSdk = AndroidConfig.targetSDK
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "SMASH_GG_API_KEY", properties.getProperty("SmashGGAPIKey"))
    }

    buildTypes {
        debug {
            isTestCoverageEnabled = true
        }

        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    testOptions {
        unitTests.all {
            it.extensions.configure(kotlinx.kover.api.KoverTaskExtension::class) {
                isEnabled = true
                excludes = listOf(
                    "dagger.hilt.internal.aggregatedroot.codegen.*",
                    "hilt_aggregated_deps.*",
                    ".*ComposableSingletons.*",
                    ".*Hilt.*",
                    ".*BuildConfig.*",
                    ".*_Factory.*",
                )
            }
        }
    }
}

dependencies {

    implementation(project(":shared"))
    implementation(project(":event-api"))
    implementation(project(":event-implementation"))
    implementation(project(":eventsummary"))
    implementation(project(":android-design-system"))
    implementation("androidx.core:core-ktx:${Versions.ktxCore}")
    implementation("androidx.appcompat:appcompat:${Versions.appCompat}")
    implementation("com.google.android.material:material:${Versions.material}")
    implementation("androidx.compose.ui:ui:${Versions.compose}")
    implementation("androidx.compose.material:material:${Versions.compose}")
    implementation("androidx.compose.material3:material3:${Versions.composeMaterial3}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.compose}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}")
    implementation("androidx.activity:activity-compose:${Versions.activityCompose}")
    implementation("com.github.murgupluoglu:flagkit-android:1.0.2")
    implementation("com.google.dagger:hilt-android:${Versions.hilt}")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("io.coil-kt:coil-compose:1.4.0")
    implementation("org.jsoup:jsoup:1.14.3")
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("com.squareup.retrofit2:converter-moshi:${Versions.retrofit}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}")
    implementation("com.squareup.moshi:moshi-kotlin:${Versions.moshi}")
    implementation("com.google.accompanist:accompanist-insets-ui:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}")
    implementation("com.apollographql.apollo3:apollo-runtime:${Versions.apollo}")
    implementation("io.github.raamcosta.compose-destinations:core:${Versions.composeDestinations}")
    implementation("androidx.navigation:navigation-compose:${Versions.composeNavigation}")
    implementation("com.google.accompanist:accompanist-pager:${Versions.accompanist}")
    implementation("com.tunjid.mutator:core:0.0.1")
    implementation("com.tunjid.mutator:coroutines:0.0.1")
    implementation("androidx.window:window:${Versions.windowManager}")
    ksp("io.github.raamcosta.compose-destinations:ksp:${Versions.composeDestinations}")
    kapt("com.google.dagger:hilt-compiler:${Versions.hilt}")
    testImplementation("junit:junit:${Versions.junit}")
    testImplementation("com.google.truth:truth:${Versions.truth}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}")
    testImplementation("app.cash.turbine:turbine:${Versions.turbine}")
    androidTestImplementation("androidx.test.ext:junit:${Versions.androidxTest}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espresso}")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.compose}")
    debugImplementation("androidx.compose.ui:ui-tooling:${Versions.compose}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${Versions.compose}")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
}

apollo {
    generateKotlinModels.set(true)

    packageName.set("com.adammcneilly.pocketleague.graphql")
}
