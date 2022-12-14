package com.example.proyecto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.databinding.RestauranteFilaBinding
import com.example.proyecto.model.Restaurante
import com.example.proyecto.ui.restaurante.RestauranteFragmentDirections
import com.example.proyecto.ui.restaurante.UpdateRestauranteFragmentDirections

class RestauranteAdapter: RecyclerView.Adapter<RestauranteAdapter.RestauranteViewHolder>() {

    private var listarRestaurantes = emptyList<Restaurante>()

    inner class RestauranteViewHolder(private var itemBinding: RestauranteFilaBinding) :
        RecyclerView.ViewHolder(itemBinding.root){

        fun dibujar(restaurante: Restaurante) {
            itemBinding.tvNombre.text = restaurante.nombre
            itemBinding.tvLocalizacion.text = restaurante.localizacion
            itemBinding.tvCocina.text = restaurante.cocina
            itemBinding.tvTelefono.text = restaurante.telefono


            //evento edit
            itemBinding.vistaFila.setOnClickListener {
                val accion = RestauranteFragmentDirections
                    .actionNavRestauranteToUpdateRestauranteFragment(restaurante)//(restaurante)
                itemView.findNavController().navigate(accion)
            }

        }
    }

    fun setRestaurantes(restaurantes: List<Restaurante>){
        listarRestaurantes = restaurantes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestauranteViewHolder{
        val itemBinding = RestauranteFilaBinding
            .inflate(LayoutInflater.from(parent.context)
                ,parent,false)
        return RestauranteViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RestauranteViewHolder, position: Int) {
        val restaurante = listarRestaurantes[position]
        holder.dibujar(restaurante)
    }

    override fun getItemCount(): Int{
        return listarRestaurantes.size
    }
//listado de restaurantea p;ciommo
}

