import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = "com.sunnychung.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "compose-desktop-app-icon-issue"
            packageVersion = "1.0.0"
            vendor = "Sunny Chung"
            copyright = "© 2023 Sunny Chung"

            macOS {
                iconFile.set(project.file("appicon/appicon.icns"))
                infoPlist {
                    extraKeysRawXml = """
                        <key>LSMinimumSystemVersion</key>
                        <string>10</string>
                    """.trimIndent()
                }
            }
            windows {
                iconFile.set(project.file("appicon/appicon.ico"))
            }
            linux {
                iconFile.set(project.file("appicon/appicon.png"))
            }
        }
    }
}
