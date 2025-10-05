// Archivo de configuración principal del proyecto
plugins {
    alias(libs.plugins.android.application) apply false // Plugin de aplicación Android, declarado pero no aplicado aquí
    alias(libs.plugins.kotlin.android) apply false      // Plugin de Kotlin para Android, disponible para módulos
    alias(libs.plugins.kotlin.compose) apply false      // Plugin de Jetpack Compose, disponible para módulos
}
