plugins {
    id("java")
    alias(libs.plugins.kotlin)
}

group = "hashi"
version = "0.1.0-SNAPSHOT"

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    repositories {
        mavenCentral()
        mavenLocal()
        google()
        maven(uri("https://oss.sonatype.org/content/repositories/snapshots/"))
        maven(uri("https://repo.papermc.io/repository/maven-public/"))
        maven(uri("https://maven.nostal.ink/repository/maven-public"))
    }
}