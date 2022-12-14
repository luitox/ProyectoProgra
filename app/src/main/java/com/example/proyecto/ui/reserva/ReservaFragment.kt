package com.example.proyecto.ui.reserva

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
import com.example.proyecto.adapter.ReservaAdapter
import com.example.proyecto.databinding.FragmentReservaBinding
import com.example.proyecto.viewmodel.ReservaViewModel

class ReservaFragment : Fragment() {

    private lateinit var  reservaViewModel: ReservaViewModel
    private var _binding: FragmentReservaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        reservaViewModel =
            ViewModelProvider(this)[ReservaViewModel::class.java]
        _binding = FragmentReservaBinding.inflate(inflater, container, false)

        binding.fbAgregar.setOnClickListener{
            findNavController().navigate(R.id.action_nav_reserva_to_addReservaFragment)  }

        //Cargar datos
        val reservaAdapter = ReservaAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter  = reservaAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        reservaViewModel.getAllData.observe(viewLifecycleOwner){
                reservas -> reservaAdapter.setReservas(reservas)
        }




        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}