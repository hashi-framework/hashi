plugins {
    id("java")
    alias(libs.plugins.kotlin)
}

group = "dev.hashimc.hashi"
version = "0.1.0-SNAPSHOT"

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    }

    kotlin {
        javaToolchains {
            version = "17"
        }
    }

    repositories {
        mavenCentral()
        mavenLocal()
        google()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://maven.nostal.ink/repository/maven-public")
    }

    dependencies {
        api(rootProject.libs.adventure.api)
        api(rootProject.libs.adventure.text.minimessage)
        api(rootProject.libs.adventure.text.serializer.gson)
        api(rootProject.libs.adventure.text.serializer.legacy)
        api(rootProject.libs.adventure.text.serializer.plain)
        api(rootProject.libs.adventure.text.serializer.ansi)
    }
}
