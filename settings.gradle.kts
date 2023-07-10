/**
 * Custom function to include a project for a given [name], this
 * allows us to call [configureBuildFileName] enabling us to specify
 * files as `module.gradle.kts` instead of several `build.gradle.kts` files.
 */
fun includeProject(name: String) {
    settings.include(name)
    val project = project(name)
    project.configureBuildFileName(name)
}

/**
 * Given a [projectName], parse out what that name looks like without
 * colons and use the format `projectName.gradle.kts`. If the file is not found,
 * default back to build.gradle.kts.
 */
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
        throw GradleException("Build file: build.gradle.kts or $name.gradle.kts does not exist. " +
            "Cannot include project: $name")
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
