package com.example.crud_room_kotlin // Define el paquete donde está la interfaz

interface AdaptadorListener { // Interfaz que define acciones para interactuar con los ítems del adaptador

    fun onEditItemClick(usuario: Usuario) // Método que se ejecuta cuando se hace clic en "editar" un usuario

    fun onDeleteItemClick(usuario: Usuario) // Método que se ejecuta cuando se hace clic en "eliminar" un usuario
}
