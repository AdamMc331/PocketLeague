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
        classpath(libs.gradle)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.detekt.gradle.plugin)
        classpath(libs.kotlin.serialization)
        classpath(libs.square.sqldelight.plugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }

    // Currently overriding the ktlint version to 1.0.1, can remove in the future when kotlinter
    // is updated: https://github.com/jeremymailen/kotlinter-gradle#custom-ktlint-version
    configurations.classpath {
        resolutionStrategy {
            force(
                "com.pinterest.ktlint:ktlint-rule-engine:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-rule-engine-core:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-core:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-checkstyle:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-json:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-html:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-plain:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-sarif:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-ruleset-standard:${libs.versions.ktlint.get()}",
            )
        }
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
    apply(from = "${rootProject.projectDir}/buildscripts/guava-fix.gradle")

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
    id("io.gitlab.arturbosch.detekt").version(libs.versions.detektGradlePlugin.get())
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlinter) apply false
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
