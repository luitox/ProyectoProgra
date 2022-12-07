package com.example.proyecto.ui.hotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentAddHotelBinding
import com.example.proyecto.databinding.FragmentAddRestauranteBinding
import com.example.proyecto.model.Hotel
import com.example.proyecto.viewmodel.RestauranteViewModel
import com.example.proyecto.model.Restaurante
import com.example.proyecto.viewmodel.HotelViewModel

class AddHotelFragment : Fragment() {

    private var _binding: FragmentAddHotelBinding? = null
    private val binding get() = _binding!!
    private lateinit var hotelViewModel: HotelViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        hotelViewModel = ViewModelProvider(this).get(HotelViewModel::class.java)
        _binding = FragmentAddHotelBinding.inflate(inflater, container, false)

        binding.btAgregar.setOnClickListener { addHotel() }

        return binding.root
    }

    //Efectivamente hace el registro del restaurante en la base de datos
    private fun addHotel() {
        val nombre=binding.etNombre.text.toString()

        if (nombre.isNotEmpty()) {   //Al menos tenemos un nombre
            val localizacion=binding.etLocalizacion.text.toString()
            val precio=binding.etPrecio.text.toString()
            val telefono=binding.etTelefono.text.toString()

            val hotel = Hotel("",nombre,localizacion,precio,telefono)
            hotelViewModel.addHotel(hotel)
            Toast.makeText(requireContext(),getString(R.string.msg_hotel_added),
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addHotelFragment_to_nav_hotel)
        } else {  //No hay info del restaurante...
            Toast.makeText(requireContext(),getString(R.string.msg_data),
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}