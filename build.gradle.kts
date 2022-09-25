import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform") version "1.5.10"
    kotlin("plugin.serialization") version "1.5.10"
    id("org.jetbrains.compose") version "0.5.0-build224"

    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
    id("com.adarshr.test-logger") version "3.2.0"
    id("net.rdrei.android.buildtimetracker") version "0.11.0"
}

group = "blue.starry"
version = "1.0"

allprojects {
    repositories {
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
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
                api(project(":modules:player-component"))
                api(project(":modules:ui-components"))

                implementation("io.ktor:ktor-client:1.6.8")
                implementation("io.ktor:ktor-client-serialization:1.6.8")
                implementation("io.ktor:ktor-client-logging:1.6.8")

                implementation("io.github.microutils:kotlin-logging:2.1.23")
            }
        }

        named("desktopMain") {
            dependencies {
                implementation(compose.desktop.currentOs)

                implementation("com.charleskorn.kaml:kaml:0.48.0")
                implementation("io.ktor:ktor-client-cio:1.6.8")

                implementation("ch.qos.logback:logback-core:1.2.11")
                implementation("ch.qos.logback:logback-classic:1.4.1")
                implementation("org.fusesource.jansi:jansi:2.4.0")
            }
        }
        named("desktopTest") {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }

    sourceSets.all {
        languageSettings.useExperimentalAnnotation("kotlin.RequiresOptIn")
    }
}

tasks.withType<Test> {
    useTestNG()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }
}

buildtimetracker {
    reporters {
        register("summary") {
            options["ordered"] = "true"
            options["barstyle"] = "ascii"
            options["shortenTaskNames"] = "false"
        }
    }
}

compose.desktop {
    application {
        mainClass = "blue.starry.teleskope.MainKt"
        javaHome = System.getenv("JDK_16")

        nativeDistributions {
            targetFormats(TargetFormat.Exe, TargetFormat.Dmg, TargetFormat.Deb)
            packageName = "Teleskope"
            packageVersion = "1.0.0"
            description = "Universal Mirakurun & EPGStation Desktop Client"
            copyright = "(c) 2021 starry.blue. All rights reserved."
            includeAllModules = true

            windows {
                dirChooser = true
                perUserInstall = true

                menu = true
                menuGroup = "Teleskope"

                // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                upgradeUuid = "6FAB797B-E8AE-4958-ACF0-E099A298C70A"
            }

            macOS {
                bundleID = "blue.starry.teleskope"
            }

            linux {
                menuGroup = "Teleskope"
            }
        }
    }
}
