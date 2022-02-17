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
        kotlinCompilerExtensionVersion = "1.1.0"
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

    implementation(project(":core-models"))
    implementation(project(":eventsummary"))
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.compose.ui:ui:${Versions.compose}")
    implementation("androidx.compose.material:material:1.1.0")
    implementation("androidx.compose.material3:material3:1.0.0-alpha05")
    implementation("androidx.compose.ui:ui-tooling-preview:1.1.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("com.github.murgupluoglu:flagkit-android:1.0.2")
    implementation("com.google.dagger:hilt-android:2.40.5")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("io.coil-kt:coil-compose:1.4.0")
    implementation("org.jsoup:jsoup:1.14.3")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    implementation("com.google.accompanist:accompanist-insets-ui:0.20.3")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.20.3")
    implementation("com.apollographql.apollo3:apollo-runtime:3.1.0")
    implementation("io.github.raamcosta.compose-destinations:core:0.9.4-beta")
    implementation("androidx.navigation:navigation-compose:2.4.0")
    implementation("com.google.accompanist:accompanist-pager:0.20.3")
    implementation("com.tunjid.mutator:core:0.0.1")
    implementation("com.tunjid.mutator:coroutines:0.0.1")
    ksp("io.github.raamcosta.compose-destinations:ksp:0.9.4-beta")
    kapt("com.google.dagger:hilt-compiler:2.40.5")
    testImplementation("junit:junit:4.13.2")
    testImplementation("com.google.truth:truth:1.1.3")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
    testImplementation("app.cash.turbine:turbine:0.7.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.1.0")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.1.0")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
}

apollo {
    generateKotlinModels.set(true)

    packageName.set("com.adammcneilly.pocketleague.graphql")
}
