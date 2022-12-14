package com.example.proyecto.ui.reserva

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentAddReservaBinding
import com.example.proyecto.viewmodel.ReservaViewModel
import com.example.proyecto.model.Reserva

class AddReservaFragment : Fragment() {

    private var _binding: FragmentAddReservaBinding? = null
    private val binding get() = _binding!!
    private lateinit var reservaViewModel: ReservaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        reservaViewModel = ViewModelProvider(this).get(ReservaViewModel::class.java)
        _binding = FragmentAddReservaBinding.inflate(inflater, container, false)

        binding.btAgregar.setOnClickListener { addReserva() }

        return binding.root
    }

    //Efectivamente hace el registro del reserva en la base de datos
    private fun addReserva() {
        val lugar=binding.etLugar.text.toString()

        if (lugar.isNotEmpty()) {   //Al menos tenemos un nombre
            val fecha=binding.etFecha.text.toString()
            val dias=binding.etDias.text.toString().toInt()
            val total=binding.etTotal.text.toString().toInt()

            val reserva = Reserva("",lugar,fecha,dias,total)
            reservaViewModel.addReserva(reserva)
            Toast.makeText(requireContext(),getString(R.string.msg_reserva_added),
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addReservaFragment_to_nav_reserva)
        } else {  //No hay info del reserva...
            Toast.makeText(requireContext(),getString(R.string.msg_data),
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}