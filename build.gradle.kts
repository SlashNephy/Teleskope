import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform") version "1.5.10"
    kotlin("plugin.serialization") version "1.5.10"
    id("org.jetbrains.compose") version "0.4.0"

    id("org.jlleitschuh.gradle.ktlint") version "10.1.0"
    id("com.adarshr.test-logger") version "3.0.0"
    id("net.rdrei.android.buildtimetracker") version "0.11.0"
}

group = "blue.starry"
version = "1.0"

repositories {
    mavenCentral()
    maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm("desktop")

    sourceSets {
        named("commonMain") {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.1")
                
                implementation("io.ktor:ktor-client:1.6.0")
                implementation("io.ktor:ktor-client-serialization:1.6.0")
                implementation("io.ktor:ktor-client-logging:1.6.0")
                
                implementation("io.github.microutils:kotlin-logging:2.0.8")
            }
        }
        
        named("desktopMain") {
            dependencies {
                implementation(compose.desktop.currentOs)
                
                implementation("uk.co.caprica:vlcj:4.7.1")
                implementation("com.charleskorn.kaml:kaml:0.34.0")
                implementation("io.ktor:ktor-client-cio:1.6.0")
                
                implementation("ch.qos.logback:logback-core:1.2.3")
                implementation("ch.qos.logback:logback-classic:1.2.3")
                implementation("org.fusesource.jansi:jansi:2.3.2")
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

//tasks.test {
//    useTestNG()
//}
//
//tasks.withType<KotlinCompile> {
//    kotlinOptions.jvmTarget = "16"
//}

compose.desktop {
    application {
        mainClass = "blue.starry.teleskope.MainKt"
        
        nativeDistributions {
            targetFormats(TargetFormat.Exe, TargetFormat.Dmg, TargetFormat.Deb)
            packageName = "Teleskope"
            packageVersion = "1.0.0"
            
            windows {
                menu = true
                // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                upgradeUuid = "6FAB797B-E8AE-4958-ACF0-E099A298C70A"
            }
        }
    }
}
