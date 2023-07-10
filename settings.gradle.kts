fun includeProject(name: String, filePath: String? = null) {
    settings.include(name)
    val project = project(name)
    project.configureProjectDir(filePath)
    project.configureBuildFileName(name)
}

fun ProjectDescriptor.configureProjectDir(filePath: String? = null) {
    if (filePath != null) {
        projectDir = File(rootDir, filePath)
    }

    if (!projectDir.exists()) {
        throw GradleException("Path: $projectDir does not exist. Cannot include project: $name")
    }

    if (!projectDir.isDirectory) {
        throw GradleException("Path: $projectDir is a file instead of a directory. Cannot include project: $name")
    }
}

fun ProjectDescriptor.configureBuildFileName(projectName: String) {
    // Project names typically start with a colon like :shared:app so we want
    // to drop the first one before replacing with periods.
    val name = projectName
        .substringAfter(":")
        .replace(":", ".")

    buildFileName = "$name.gradle.kts"

    if (!buildFile.exists()) {
        buildFileName = "build.gradle.kts"
    }

    if (!buildFile.exists()) {
        throw GradleException("Build file: build.gradle.kts or $name.gradle.kts does not exist. Cannot include project: $name")
    }
}

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "PocketLeague"

includeProject(":android:app")
includeProject(":android:appv2")
includeProject(":android:baselineprofile")
includeProject(":android:design-system")
includeProject(":android:widgets")

includeProject(":core:currency")
includeProject(":core:datetime")
includeProject(":core:datetime-test")
includeProject(":core:displaymodels")
includeProject(":core:displaymodels-test")
includeProject(":core:feature")
includeProject(":core:locale")
includeProject(":core:models")
includeProject(":core:models-test")

includeProject(":data:event")
includeProject(":data:event-test")
includeProject(":data:game")
includeProject(":data:local-sqldelight")
includeProject(":data:match")
includeProject(":data:match-test")
includeProject(":data:octanegg")
includeProject(":data:remote")
includeProject(":data:remote-test")
includeProject(":data:team")
includeProject(":data:team-test")

includeProject(":feature:event-detail")
includeProject(":feature:feed")

includeProject(":shared")
includeProject(":shared:app")
includeProject(":shared:design-system")
includeProject(":shared:ui")
includeProject(":shared-test")
includeProject(":data:startgg")
