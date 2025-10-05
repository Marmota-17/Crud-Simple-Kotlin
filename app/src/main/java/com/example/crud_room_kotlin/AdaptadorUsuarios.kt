package com.example.crud_room_kotlin // Paquete donde está definida la clase

import android.view.LayoutInflater // Para inflar layouts XML en vistas
import android.view.View // Representa una vista en Android
import android.view.ViewGroup // Contenedor de vistas, usado en RecyclerView
import android.widget.Button // Widget de botón
import android.widget.TextView // Widget de texto
import androidx.cardview.widget.CardView // Vista con estilo de tarjeta
import androidx.recyclerview.widget.RecyclerView // Componente para listas dinámicas

class AdaptadorUsuarios( // Clase adaptador para mostrar usuarios en un RecyclerView
    val listaUsuarios: MutableList<Usuario>, // Lista de usuarios que se mostrarán
    val listener: AdaptadorListener // Listener para manejar eventos de clic
): RecyclerView.Adapter<AdaptadorUsuarios.ViewHolder>() { // Hereda de RecyclerView.Adapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Crea una nueva vista (ViewHolder) inflando el layout del ítem
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_usuario, parent, false)
        return ViewHolder(vista) // Devuelve el ViewHolder con la vista inflada
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Asigna los datos de un usuario a los elementos visuales del ViewHolder
        val usuario = listaUsuarios[position] // Obtiene el usuario en la posición actual

        holder.tvUsuario.text = usuario.usuario // Muestra el nombre del usuario
        holder.tvPais.text = usuario.pais // Muestra el país del usuario

        holder.cvUsuario.setOnClickListener { // Si se hace clic en la tarjeta
            listener.onEditItemClick(usuario) // Llama al listener para editar el usuario
        }

        holder.btnBorrar.setOnClickListener { // Si se hace clic en el botón borrar
            listener.onDeleteItemClick(usuario) // Llama al listener para eliminar el usuario
        }
    }

    override fun getItemCount(): Int {
        // Devuelve la cantidad de elementos en la lista
        return listaUsuarios.size
    }

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
        // Clase interna que representa cada ítem de la lista
        val cvUsuario = itemView.findViewById<CardView>(R.id.cvUsuario) // CardView del usuario
        val tvUsuario = itemView.findViewById<TextView>(R.id.tvUsuario) // TextView para el nombre
        val tvPais = itemView.findViewById<TextView>(R.id.tvPais) // TextView para el país
        val btnBorrar = itemView.findViewById<Button>(R.id.btnBorrar) // Botón para borrar
    }

}
