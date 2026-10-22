rootProject.name = "SyncHub"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":core:database")
include(":core:network")
include(":shared:domain")
include(":shared:data")

project(":shared:domain").projectDir = file("shared/domain")
project(":shared:data").projectDir = file("shared/data")
project(":core:database").projectDir = file("core/database")
project(":core:network").projectDir = file("core/network")
