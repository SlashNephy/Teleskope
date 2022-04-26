plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm("desktop")

    sourceSets {
        named("commonMain") {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                
                api(project(":modules:api"))
            }
        }
        named("desktopMain") {
            dependencies {
                api("uk.co.caprica:vlcj:4.7.2")
            }
        }
    }
}
