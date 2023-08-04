pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "PocketLeague"

include(":android:app")
include(":android:widgets")

include(":core:currency")
include(":core:datetime")
include(":core:datetime-test")
include(":core:displaymodels")
include(":core:displaymodels-test")
include(":core:feature")
include(":core:locale")
include(":core:models")
include(":core:models-test")
include(":core:test")

include(":data:event")
include(":data:event-test")
include(":data:game")
include(":data:local-sqldelight")
include(":data:match")
include(":data:match-test")
include(":data:octanegg")
include(":data:remote")
include(":data:remote-test")
include(":data:startgg")
include(":data:team")
include(":data:team-test")

include(":shared:app")
include(":shared:design-system")
include(":shared:ui")

include(":data:match:api")
include(":data:match:impl")