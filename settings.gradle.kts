pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}
rootProject.name = "hashi"

include("core", "core:core-common")
include("loader")
include("bootstrap")
