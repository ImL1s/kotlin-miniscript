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
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "kotlin-miniscript"

val cryptoPure = listOf("../kotlin-crypto-pure", "./kotlin-crypto-pure")
    .map { file(it) }
    .firstOrNull { it.exists() }
if (cryptoPure != null) {
    includeBuild(cryptoPure)
}

val address = listOf("../kotlin-address", "./kotlin-address")
    .map { file(it) }
    .firstOrNull { it.exists() }
if (address != null) {
    includeBuild(address)
}
