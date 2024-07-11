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
}
