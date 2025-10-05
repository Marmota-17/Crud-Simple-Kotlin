package com.example.crud_room_kotlin // Paquete principal de la app

import androidx.appcompat.app.AppCompatActivity // Clase base para actividades con compatibilidad
import android.os.Bundle // Manejo del ciclo de vida de la actividad
import android.widget.Toast // Para mostrar mensajes cortos en pantalla
import androidx.lifecycle.lifecycleScope // Corrutinas ligadas al ciclo de vida
import androidx.recyclerview.widget.LinearLayoutManager // Layout para listas en RecyclerView
import androidx.room.Room // Para crear la base de datos Room
import com.example.crud_room_kotlin.databinding.ActivityMainBinding // ViewBinding para acceder a vistas
import kotlinx.coroutines.launch // Para lanzar corrutinas

class MainActivity : AppCompatActivity(), AdaptadorListener { // Actividad principal que implementa el listener del adaptador

    lateinit var binding: ActivityMainBinding // Enlace a las vistas del layout

    var listaUsuarios: MutableList<Usuario> = mutableListOf() // Lista de usuarios mutable

    lateinit var adatador: AdaptadorUsuarios // Adaptador para el RecyclerView

    lateinit var room: DBPrueba // Instancia de la base de datos Room

    lateinit var usuario: Usuario // Usuario actual en edición o creación
    // Método que se ejecuta al crear la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Infla el layout con ViewBinding
        setContentView(binding.root) // Asigna el layout a la actividad

        binding.rvUsuarios.layoutManager = LinearLayoutManager(this) // Configura el RecyclerView en forma de lista vertical

        room = Room.databaseBuilder(this, DBPrueba::class.java, "dbPruebas").build() // Crea la base de datos Room

        obtenerUsuarios(room) // Carga los usuarios desde la base de datos

        binding.btnAddUpdate.setOnClickListener { // Acción al presionar el botón agregar/actualizar
            if(binding.etUsuario.text.isNullOrEmpty() || binding.etPais.text.isNullOrEmpty()) { // Valida que los campos no estén vacíos
                Toast.makeText(this, "DEBES LLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show() // Muestra mensaje de error
                return@setOnClickListener // Sale de la función si hay campos vacíos
            }

            if (binding.btnAddUpdate.text.equals("agregar")) { // Si el botón está en modo "agregar"
                usuario = Usuario( // Crea un nuevo usuario con los datos ingresados
                    binding.etUsuario.text.toString().trim(),
                    binding.etPais.text.toString().trim()
                )

                agregarUsuario(room, usuario) // Llama a la función para agregar el usuario
            } else if(binding.btnAddUpdate.text.equals("actualizar")) { // Si el botón está en modo "actualizar"
                usuario.pais = binding.etPais.text.toString().trim() // Actualiza el país del usuario

                actualizarUsuario(room, usuario) // Llama a la función para actualizar el usuario
            }
        }

    }

    fun obtenerUsuarios(room: DBPrueba) { // Función para obtener todos los usuarios
        lifecycleScope.launch { // Lanza una corrutina
            listaUsuarios = room.daoUsuario().obtenerUsuarios() // Obtiene la lista desde la base de datos
            adatador = AdaptadorUsuarios(listaUsuarios, this@MainActivity) // Crea el adaptador con la lista
            binding.rvUsuarios.adapter = adatador // Asigna el adaptador al RecyclerView
        }
    }

    fun agregarUsuario(room: DBPrueba, usuario: Usuario) { // Función para agregar un usuario
        lifecycleScope.launch {
            room.daoUsuario().agregarUsuario(usuario) // Inserta el usuario en la base de datos
            obtenerUsuarios(room) // Refresca la lista
            limpiarCampos() // Limpia los campos del formulario
        }
    }

    fun actualizarUsuario(room: DBPrueba, usuario: Usuario) { // Función para actualizar un usuario
        lifecycleScope.launch {
            room.daoUsuario().actualizarUsuario(usuario.usuario, usuario.pais) // Actualiza en la base de datos
            obtenerUsuarios(room) // Refresca la lista
            limpiarCampos() // Limpia los campos del formulario
        }
    }

    fun limpiarCampos() { // Función para limpiar los campos del formulario
        usuario.usuario = "" // Limpia el nombre del usuario
        usuario.pais = "" // Limpia el país
        binding.etUsuario.setText("") // Borra el texto del campo usuario
        binding.etPais.setText("") // Borra el texto del campo país

        if (binding.btnAddUpdate.text.equals("actualizar")) { // Si el botón estaba en modo actualizar
            binding.btnAddUpdate.setText("agregar") // Lo cambia a modo agregar
            binding.etUsuario.isEnabled = true // Habilita nuevamente el campo usuario
        }

    }

    override fun onEditItemClick(usuario: Usuario) { // Acción al hacer clic en editar un usuario
        binding.btnAddUpdate.setText("actualizar") // Cambia el botón a modo actualizar
        binding.etUsuario.isEnabled = false // Deshabilita el campo usuario (no se puede cambiar la clave primaria)
        this.usuario = usuario // Asigna el usuario seleccionado
        binding.etUsuario.setText(this.usuario.usuario) // Muestra el nombre en el campo
        binding.etPais.setText(this.usuario.pais) // Muestra el país en el campo
    }

    override fun onDeleteItemClick(usuario: Usuario) { // Acción al hacer clic en eliminar un usuario
        lifecycleScope.launch {
            room.daoUsuario().borrarUsuario(usuario.usuario) // Elimina el usuario de la base de datos
            adatador.notifyDataSetChanged() // Notifica cambios al adaptador
            obtenerUsuarios(room) // Refresca la lista
        }
    }
}
