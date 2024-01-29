enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "PocketLeague"

include(":android:app")

include(":core:currency")
include(":core:data")
include(":core:datetime")
include(":core:datetime-test")
include(":core:displaymodels")
include(":core:displaymodels-test")
include(":core:feature")
include(":core:locale")
include(":core:models")
include(":core:models-test")
include(":core:test")

include(":data:event:api")
include(":data:event:impl")
include(":data:event:test")
include(":data:game")
include(":data:local-sqldelight")
include(":data:match:api")
include(":data:match:impl")
include(":data:match:test")
include(":data:octanegg")
include(":data:player")
include(":data:remote")
include(":data:remote-test")
include(":data:startgg")
include(":data:team")
include(":data:team-test")

include(":feature:debugmenu")
include(":feature:eventdetail")
include(":feature:teamdetail")

include(":shared:app")
include(":shared:design-system")
include(":shared:ui")
