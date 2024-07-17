plugins {
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.compose)
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.plugin.compose")
    apply(plugin = "org.jetbrains.compose")

    dependencies {
        api(compose.runtime) {
            exclude("org.jetbrains.kotlin")
            exclude("org.jetbrains.kotlinx")
        }
    }
}