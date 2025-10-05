package com.example.crud_room_kotlin // Define el paquete donde está la interfaz

import androidx.room.Dao // Importa la anotación @Dao para marcar la interfaz como DAO de Room
import androidx.room.Insert // Importa la anotación @Insert para insertar registros
import androidx.room.Query // Importa la anotación @Query para ejecutar consultas SQL

@Dao // Indica que esta interfaz es un Data Access Object (DAO) para Room
interface DaoUsuario { // Interfaz que define las operaciones sobre la tabla de usuarios

    @Query("SELECT * FROM usuarios") // Consulta SQL que obtiene todos los registros de la tabla "usuarios"
    suspend fun obtenerUsuarios(): MutableList<Usuario> // Función suspendida que devuelve la lista de usuarios

    @Insert // Anotación que indica que esta función inserta un registro en la tabla
    suspend fun agregarUsuario(usuario: Usuario) // Inserta un objeto Usuario en la base de datos

    @Query("UPDATE usuarios set pais=:pais WHERE usuario=:usuario") // Consulta SQL que actualiza el campo "pais" de un usuario específico
    suspend fun actualizarUsuario(usuario: String, pais: String) // Actualiza el país de un usuario según su nombre

    @Query("DELETE FROM usuarios WHERE usuario=:usuario") // Consulta SQL que elimina un usuario por su nombre
    suspend fun borrarUsuario(usuario: String) // Elimina un usuario de la base de datos
}
