import org.jetbrains.kotlin.gradle.targets.js.testing.KotlinJsTest
import org.jetbrains.kotlin.gradle.targets.jvm.tasks.KotlinJvmTest
import org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeTest
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.21.0")
        classpath("com.github.ben-manes:gradle-versions-plugin:0.42.0")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.9.0")
        classpath(libs.square.sqldelight.plugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

apply(from = "buildscripts/githooks.gradle")

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

    afterEvaluate {
        gradle.projectsEvaluated {
            tasks.withType(JavaCompile::class.java) {
                sourceCompatibility = "17"
                targetCompatibility = "17"
            }
        }
    }

    // Following this: https://publicobject.com/2023/04/16/read-a-project-file-in-a-kotlin-multiplatform-test/
    tasks.withType<KotlinJvmTest>().configureEach {
        environment("TEST_DATA_ROOT", "$projectDir/src/commonTest/resources/")
    }

    tasks.withType<KotlinNativeTest>().configureEach {
        environment("SIMCTL_CHILD_TEST_DATA_ROOT", "$projectDir/src/commonTest/resources/")
        environment("TEST_DATA_ROOT", "$projectDir/src/commonTest/resources/")
    }

    tasks.withType<KotlinJsTest>().configureEach {
        environment("TEST_DATA_ROOT", "$projectDir/src/commonTest/resources/")
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

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs += listOf(
            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true",
        )
    }
}
