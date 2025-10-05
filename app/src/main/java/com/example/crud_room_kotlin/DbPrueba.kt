package com.example.crud_room_kotlin // Define el paquete donde está la clase

import androidx.room.Database // Importa la anotación @Database para declarar la base de datos
import androidx.room.RoomDatabase // Importa la clase base RoomDatabase que se extiende

@Database( // Anotación que indica que esta clase es una base de datos de Room
    entities = [Usuario::class], // Lista de entidades (tablas) que usará la base de datos, en este caso "Usuario"
    version = 1 // Versión de la base de datos, necesaria para manejar migraciones
)
abstract class DBPrueba : RoomDatabase() { // Clase abstracta que extiende RoomDatabase
    abstract fun daoUsuario(): DaoUsuario // Método abstracto que expone el DAO para acceder a la tabla de usuarios
}
