package com.example.crud_room_kotlin
// Define el paquete donde se encuentra la clase Usuario

import androidx.room.ColumnInfo // Importa la anotación para personalizar el nombre de columnas en la tabla
import androidx.room.Entity // Importa la anotación para declarar una entidad de Room
import androidx.room.PrimaryKey // Importa la anotación para definir la clave primaria de la tabla

@Entity(tableName = "usuarios")
// Declara que esta clase es una entidad de Room y que se almacenará en la tabla "usuarios"

data class Usuario(
    // Clase de datos que representa la tabla "usuarios"

    @PrimaryKey var usuario: String,
    // Campo "usuario" será la clave primaria de la tabla.
    // Es único y no puede repetirse.

    @ColumnInfo(name = "pais") var pais: String
    // Campo "pais" se guardará en la columna llamada "pais" dentro de la tabla.
)
