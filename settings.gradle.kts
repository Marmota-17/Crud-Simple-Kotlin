pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*") // Incluye plugins cuyo grupo empieza con "com.android"
                includeGroupByRegex("com\\.google.*")  // Incluye plugins de Google
                includeGroupByRegex("androidx.*")      // Incluye plugins de AndroidX
            }
        }
        mavenCentral()        // Repositorio Maven Central para plugins
        gradlePluginPortal()  // Repositorio oficial de plugins de Gradle
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    // Fuerza a que los repositorios se definan aquí y no en cada módulo

    repositories {
        google()       // Repositorio de Google (AndroidX, librerías oficiales)
        mavenCentral() // Repositorio Maven Central (librerías de terceros)
    }
}

rootProject.name = "CRUD_Room_Kotlin" // Nombre del proyecto raíz
include(":app")                       // Incluye el módulo "app" en la compilación
