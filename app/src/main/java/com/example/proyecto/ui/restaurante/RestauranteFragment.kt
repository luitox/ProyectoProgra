package com.example.proyecto.ui.restaurante

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto.R
import com.example.proyecto.adapter.RestauranteAdapter
import com.example.proyecto.databinding.FragmentRestauranteBinding
import com.example.proyecto.viewmodel.RestauranteViewModel

class RestauranteFragment : Fragment() {

    private lateinit var  restauranteViewModel: RestauranteViewModel
    private var _binding: FragmentRestauranteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        restauranteViewModel =
            ViewModelProvider(this)[RestauranteViewModel::class.java]
        _binding = FragmentRestauranteBinding.inflate(inflater, container, false)

        binding.fbAgregar.setOnClickListener{
            findNavController().navigate(R.id.action_nav_restaurante_to_addRestauranteFragment)  }

        //Cargar datos
        val restauranteAdapter = RestauranteAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter  = restauranteAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        restauranteViewModel.getAllData.observe(viewLifecycleOwner){
                restaurantes -> restauranteAdapter.setRestaurantes(restaurantes)
        }




        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}