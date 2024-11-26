import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import java.io.FileInputStream
import java.util.Properties

plugins {
    kotlin("multiplatform")
    id("com.apollographql.apollo3").version(libs.versions.apollo)
    id("com.codingfeline.buildkonfig").version(libs.versions.buildkonfig)
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.datetime)
                implementation(projects.core.models)
                implementation(libs.apollo.runtime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

apollo {
    service("service") {
        packageName.set("com.adammcneilly.pocketleague.data.startgg")
        generateApolloMetadata.set(true)
    }
}

buildkonfig {
    packageName = "com.adammcneilly.pocketleague.data.startgg"

    val properties = Properties()
    properties.load(FileInputStream(project.rootProject.file("local.properties")))

    defaultConfigs {
        buildConfigField(STRING, "START_GG_API_KEY", properties["START_GG_API_KEY"].toString())
    }
}

tasks.formatKotlinCommonMain {
    exclude { it.file.path.contains("build/")}
}

tasks.lintKotlinCommonMain {
    exclude { it.file.path.contains("build/")}
}
