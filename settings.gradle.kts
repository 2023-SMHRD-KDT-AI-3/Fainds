pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        jcenter()
        maven { url = uri("https://www.jitpack.io" ) }
        mavenCentral()
    }
}

rootProject.name = "FAINDSApplication"
include(":app")
