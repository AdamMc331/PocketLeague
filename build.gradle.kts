// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://jitpack.io")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.21.0")
        classpath("com.github.ben-manes:gradle-versions-plugin:0.42.0")
        classpath("com.hiya:jacoco-android:0.2")
        classpath("gradle.plugin.org.kt3k.gradle.plugin:coveralls-gradle-plugin:2.12.0")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.8.20")
        classpath("org.jetbrains.kotlinx:kover:0.6.1")
        classpath("com.karumi:shot:5.14.1")
        classpath(libs.square.sqldelight.plugin)
        // Workaround: https://youtrack.jetbrains.com/issue/KT-44884/Kotlin-Native-Compiler-could-not-find-org.jetbrains.kotlinxatomicfu-cinterop-interop-when-caches-are-enabled#focus=Comments-27-7300749.0-0
        classpath("org.jetbrains.kotlinx:atomicfu-gradle-plugin:0.21.0")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

apply(from = "buildscripts/githooks.gradle")
apply(from = "${rootProject.projectDir}/buildscripts/kover.gradle")

allprojects {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven(url = "https://packages.jetbrains.team/maven/p/ij/intellij-dependencies")
    }
}

subprojects {
    apply(from = "${rootProject.projectDir}/buildscripts/ktlint.gradle")
    apply(from = "${rootProject.projectDir}/buildscripts/versionsplugin.gradle")
    apply(from = "${rootProject.projectDir}/buildscripts/kover.gradle")

    afterEvaluate {
        gradle.projectsEvaluated {
            tasks.withType(JavaCompile::class.java) {
                sourceCompatibility = "17"
                targetCompatibility = "17"
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

afterEvaluate {
    // We install the hook at the first occasion
    tasks.named("clean") {
        dependsOn(":installGitHooks")
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt").version("1.0.1")
    id("org.jmailen.kotlinter").version("3.13.0").apply(false)
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
}

tasks {
    val detektAll by registering(io.gitlab.arturbosch.detekt.Detekt::class) {
        parallel = true
        setSource(files(projectDir))
        include("**/*.kt")
        include("**/*.kts")
        exclude("**/resources/**")
        exclude("**/build/**")
        config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
        buildUponDefaultConfig = false
    }
}
