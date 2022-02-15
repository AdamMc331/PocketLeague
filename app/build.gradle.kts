import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.apollographql.apollo3").version("3.1.0")
    id("com.google.devtools.ksp").version("1.6.10-1.0.2")
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
    compileSdk = 31

    val properties = Properties()
    properties.load(FileInputStream(project.rootProject.file("local.properties")))

    defaultConfig {
        applicationId = "com.adammcneilly.pocketleague"
        minSdk = 21
        targetSdk = 31
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
//            testCoverageEnabled.set(true)
        }

        release {
//            minifyEnabled = false
//            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
//        coreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
//        useIR = true
    }

    buildFeatures {
        compose = true
    }

//    composeOptions {
//        kotlinCompilerExtensionVersion = rootProject.ext.versions.compose
//        kotlinCompilerVersion = kotlinVersion
//    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    testOptions {
        unitTests.all {
            kover {
                enabled = true
                excludes = [
                        "dagger.hilt.internal.aggregatedroot.codegen.*",
                        "hilt_aggregated_deps.*",
                        ".*ComposableSingletons.*",
                        ".*Hilt.*",
                        ".*BuildConfig.*",
                        ".*_Factory.*",
                ]
            }
        }
    }
}

dependencies {

    implementation(project(":core-models"))
    implementation(project(":eventsummary"))
    implementation("androidx.core:core-ktx:${rootProject.ext.versions.ktxCore}")
    implementation("androidx.appcompat:appcompat:${rootProject.ext.versions.appCompat}")
    implementation("com.google.android.material:material:${rootProject.ext.versions.material}")
    implementation("androidx.compose.ui:ui:${rootProject.ext.versions.compose}")
    implementation("androidx.compose.material:material:${rootProject.ext.versions.compose}")
    implementation("androidx.compose.material3:material3:${rootProject.ext.versions.composeMaterial3}")
    implementation("androidx.compose.ui:ui-tooling-preview:${rootProject.ext.versions.compose}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${rootProject.ext.versions.lifecycle}")
    implementation("androidx.activity:activity-compose:${rootProject.ext.versions.activityCompose}")
    implementation("com.github.murgupluoglu:flagkit-android:1.0.2")
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("io.coil-kt:coil-compose:1.4.0")
    implementation("org.jsoup:jsoup:1.14.3")
    implementation("com.squareup.retrofit2:retrofit:${rootProject.ext.versions.retrofit}")
    implementation("com.squareup.retrofit2:converter-moshi:${rootProject.ext.versions.retrofit}")
    implementation("com.squareup.okhttp3:logging-interceptor:${rootProject.ext.versions.okhttp}")
    implementation("com.squareup.moshi:moshi-kotlin:${rootProject.ext.versions.moshi}")
    implementation("com.google.accompanist:accompanist-insets-ui:${rootProject.ext.versions.accompanist}")
    implementation("com.google.accompanist:accompanist-systemuicontroller:${rootProject.ext.versions.accompanist}")
    implementation("com.apollographql.apollo3:apollo-runtime:3.1.0")
    implementation("io.github.raamcosta.compose-destinations:core:${rootProject.ext.versions.composeDestinations}")
    implementation("androidx.navigation:navigation-compose:${rootProject.ext.versions.composeNavigation}")
    implementation("com.google.accompanist:accompanist-pager:${rootProject.ext.versions.accompanist}")
    implementation("com.tunjid.mutator:core:0.0.1")
    implementation("com.tunjid.mutator:coroutines:0.0.1")
    ksp("io.github.raamcosta.compose-destinations:ksp:${rootProject.ext.versions.composeDestinations}")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")
    testimplementation("junit:junit:${rootProject.ext.versions.junit}")
    testimplementation("com.google.truth:truth:${rootProject.ext.versions.truth}")
    testimplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${rootProject.ext.versions.coroutines}")
    testimplementation("app.cash.turbine:turbine:${rootProject.ext.versions.turbine}")
    androidTestimplementation("androidx.test.ext:junit:${rootProject.ext.versions.androidxTest}")
    androidTestimplementation("androidx.test.espresso:espresso-core:${rootProject.ext.versions.espresso}")
    androidTestimplementation("androidx.compose.ui:ui-test-junit4:${rootProject.ext.versions.compose}")
    debugimplementation("androidx.compose.ui:ui-tooling:${rootProject.ext.versions.compose}")
    debugimplementation("androidx.compose.ui:ui-test-manifest:${rootProject.ext.versions.compose}")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
}

apollo {
    generateKotlinModels = true

    packageName.set("com.adammcneilly.pocketleague.graphql")
}
