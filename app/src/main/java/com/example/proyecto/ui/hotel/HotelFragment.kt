package com.example.proyecto.ui.hotel

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
import com.example.proyecto.adapter.HotelAdapter
import com.example.proyecto.databinding.FragmentHotelBinding
import com.example.proyecto.viewmodel.HotelViewModel

class HotelFragment : Fragment() {

    private lateinit var  hotelViewModel: HotelViewModel
    private var _binding: FragmentHotelBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        hotelViewModel =
            ViewModelProvider(this)[HotelViewModel::class.java]
        _binding = FragmentHotelBinding.inflate(inflater, container, false)

        binding.fbAgregar.setOnClickListener{
            findNavController().navigate(R.id.action_nav_hotel_to_addHotelFragment)  }

        //Cargar datos
        val hotelAdapter = HotelAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter  = hotelAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        hotelViewModel.getAllData.observe(viewLifecycleOwner){
                hoteles -> hotelAdapter.setHoteles(hoteles)
        }




        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}