package com.example.proyecto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.databinding.ReservaFilaBinding
import com.example.proyecto.databinding.RestauranteFilaBinding
import com.example.proyecto.model.Reserva
import com.example.proyecto.ui.reserva.ReservaFragmentDirections
import com.example.proyecto.model.Restaurante
import com.example.proyecto.ui.restaurante.RestauranteFragmentDirections
import com.example.proyecto.ui.restaurante.UpdateRestauranteFragmentDirections

class ReservaAdapter: RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder>() {

    private var listarReservas = emptyList<Reserva>()

    inner class ReservaViewHolder(private var itemBinding: ReservaFilaBinding) :
        RecyclerView.ViewHolder(itemBinding.root){

        fun dibujar(reserva: Reserva) {
            itemBinding.tvLugar.text = reserva.lugar
            itemBinding.tvFecha.text = reserva.fecha
            itemBinding.tvDias.text = reserva.dias.toString()
            itemBinding.tvTotal.text = reserva.total.toString()

            //evento edit
            itemBinding.vistaFila.setOnClickListener {
                val accion = ReservaFragmentDirections
                    .actionNavReservaToUpdateReservaFragment(reserva)//(reserva)
                itemView.findNavController().navigate(accion)
            }
// uhu
        }
    }

    fun setReservas(reservas: List<Reserva>){
        listarReservas = reservas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaViewHolder{
        val itemBinding = ReservaFilaBinding
            .inflate(LayoutInflater.from(parent.context)
                ,parent,false)
        return ReservaViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ReservaViewHolder, position: Int) {
        val reserva = listarReservas[position]
        holder.dibujar(reserva)
    }

    override fun getItemCount(): Int{
        return listarReservas.size
    }
//listado de reservas
}

