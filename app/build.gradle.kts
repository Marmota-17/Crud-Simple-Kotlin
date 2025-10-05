plugins {
    alias(libs.plugins.android.application) // Usa el plugin de aplicación Android
    alias(libs.plugins.kotlin.android)      // Usa el plugin de Kotlin para Android
    alias(libs.plugins.kotlin.compose)      // Usa el plugin de Kotlin para Jetpack Compose
    id("kotlin-kapt")                       // Activa KAPT (procesador de anotaciones de Kotlin)
}

android {
    namespace = "com.example.crud_room_kotlin" // Define el namespace del proyecto
    compileSdk = 36                            // Versión del SDK con la que se compila

    defaultConfig {
        applicationId = "com.example.crud_room_kotlin" // ID único de la app
        minSdk = 23                                    // Versión mínima de Android soportada
        targetSdk = 36                                 // Versión objetivo de Android
        versionCode = 1                                // Número interno de versión
        versionName = "1.0"                            // Nombre visible de la versión

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" // Runner para tests
    }

    buildTypes {
        release {
            isMinifyEnabled = false // No se minimiza el código en release
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), // Configuración base de ProGuard
                "proguard-rules.pro"                                    // Reglas personalizadas de ProGuard
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11 // Compatibilidad con Java 11
        targetCompatibility = JavaVersion.VERSION_11 // Genera bytecode para Java 11
    }
    kotlinOptions {
        jvmTarget = "11" // Kotlin compila para JVM 11
    }
    buildFeatures {
        viewBinding = true // Activa ViewBinding
        compose = true     // Activa Jetpack Compose
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)                 // Extensiones de Kotlin para Android
    implementation(libs.androidx.lifecycle.runtime.ktx)    // Soporte de ciclo de vida con corrutinas
    implementation(libs.androidx.activity.compose)         // Integración de Compose con Activity
    implementation(platform(libs.androidx.compose.bom))    // BOM para manejar versiones de Compose
    implementation(libs.androidx.compose.ui)               // Librería base de UI de Compose
    implementation(libs.androidx.compose.ui.graphics)      // Soporte gráfico en Compose
    implementation(libs.androidx.compose.ui.tooling.preview) // Vista previa de Compose
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.appcompat)        // Componentes Material Design 3
    testImplementation(libs.junit)                         // Librería JUnit para pruebas unitarias
    androidTestImplementation(libs.androidx.junit)         // JUnit para pruebas instrumentadas
    androidTestImplementation(libs.androidx.espresso.core) // Espresso para pruebas de UI
    androidTestImplementation(platform(libs.androidx.compose.bom)) // BOM de Compose en pruebas
    androidTestImplementation(libs.androidx.compose.ui.test.junit4) // Pruebas de UI en Compose
    debugImplementation(libs.androidx.compose.ui.tooling)  // Herramientas de depuración de Compose
    debugImplementation(libs.androidx.compose.ui.test.manifest) // Manifesto de pruebas en debug
// Material 3 para Compose
    implementation("androidx.compose.material3:material3:1.3.0")

// Material Components clásico (para XML y AppCompat)
    implementation("com.google.android.material:material:1.12.0")

    //room
    implementation("androidx.room:room-ktx:2.6.1")   // Room con soporte para corrutinas
    kapt("androidx.room:room-compiler:2.6.1")        // Procesador de anotaciones de Room

    //Corutina
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6") // Corrutinas en ciclo de vida

}
