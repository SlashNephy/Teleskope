pluginManagement {
    repositories {
        gradlePluginPortal()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "Teleskope"

include(":modules:api")
include(":modules:player-component")
include(":modules:ui-components")
