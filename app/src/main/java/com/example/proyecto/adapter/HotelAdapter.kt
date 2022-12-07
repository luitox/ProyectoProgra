package com.example.proyecto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.databinding.HotelFilaBinding
import com.example.proyecto.databinding.RestauranteFilaBinding
import com.example.proyecto.model.Hotel
import com.example.proyecto.model.Restaurante
import com.example.proyecto.ui.restaurante.RestauranteFragmentDirections
import com.example.proyecto.ui.restaurante.UpdateRestauranteFragmentDirections

class HotelAdapter: RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {

    private var listarHoteles = emptyList<Hotel>()

    inner class HotelViewHolder(private var itemBinding: HotelFilaBinding) :
        RecyclerView.ViewHolder(itemBinding.root){

        fun dibujar(hotel: Hotel) {
            itemBinding.tvNombre.text = hotel.nombre
            itemBinding.tvLocalizacion.text = hotel.localizacion
            itemBinding.tvCocina.text = hotel.precio
            itemBinding.tvTelefono.text = hotel.telefono

            //evento edit
            itemBinding.vistaFila.setOnClickListener {
                val accion = HotelFragmentDirections
                    .actionNavHotelToUpdateHotelFragment(hotel)//(restaurante)
                itemView.findNavController().navigate(accion)
            }

        }
    }

    fun setHoteles(hoteles: List<Hotel>){
        listarHoteles = hoteles
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder{
        val itemBinding = HotelFilaBinding
            .inflate(LayoutInflater.from(parent.context)
                ,parent,false)
        return HotelViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val hotel = listarHoteles[position]
        holder.dibujar(hotel)
    }

    override fun getItemCount(): Int{
        return listarHoteles.size
    }
//listado de restaurantea p;ciommo
}

