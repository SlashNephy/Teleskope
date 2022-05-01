plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    jvm("desktop")

    sourceSets {
        named("commonMain") {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
            }
        }
    }
}
