package com.example.proyecto.ui.restaurante

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentAddRestauranteBinding
import com.example.proyecto.viewmodel.RestauranteViewModel
import com.example.proyecto.model.Restaurante
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AddRestauranteFragment : Fragment() {

    private var _binding: FragmentAddRestauranteBinding? = null
    private val binding get() = _binding!!
    private lateinit var restauranteViewModel: RestauranteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        restauranteViewModel = ViewModelProvider(this).get(RestauranteViewModel::class.java)
        _binding = FragmentAddRestauranteBinding.inflate(inflater, container, false)

        binding.btAgregar.setOnClickListener { addRestaurante() }

        return binding.root
    }

    //Efectivamente hace el registro del restaurante en la base de datos
    private fun addRestaurante() {
        val nombre=binding.etNombre.text.toString()

        if (nombre.isNotEmpty()) {   //Al menos tenemos un nombre
            val localizacion=binding.etLocalizacion.text.toString()
            val cocina=binding.etCocina.text.toString()
            val telefono=binding.etTelefono.text.toString()
            val usuario = Firebase.auth.currentUser?.email
            val codigoUsuario = "$usuario"

            val restaurante = Restaurante("",nombre,localizacion,cocina,telefono,codigoUsuario)
            restauranteViewModel.addRestaurante(restaurante)
            Toast.makeText(requireContext(),getString(R.string.msg_restaurante_added),
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addRestauranteFragment_to_nav_restaurante)
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