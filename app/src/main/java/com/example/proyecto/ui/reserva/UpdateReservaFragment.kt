package com.example.proyecto.ui.reserva

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentUpdateReservaBinding
import com.example.proyecto.model.Reserva
import com.example.proyecto.viewmodel.ReservaViewModel

class UpdateReservaFragment : Fragment() {

    //recupera argumentos
    private val args by navArgs<UpdateReservaFragmentArgs>()

    private var _binding: FragmentUpdateReservaBinding? = null
    private val binding get() = _binding!!
    private lateinit var reservaViewModel: ReservaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        reservaViewModel = ViewModelProvider(this).get(ReservaViewModel :: class.java)
        _binding = FragmentUpdateReservaBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment

        //cargar los valores edit
        binding.etLugar.setText(args.reserva.lugar)
        binding.etFecha.setText(args.reserva.fecha)
        binding.etDias.setText(args.reserva.dias.toString())
        binding.etTotal.setText("Precio Total:" + args.reserva.total.toString())

        binding.btUpdate.setOnClickListener{ updateReserva() }

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun updateReserva(){
        val lugar = binding.etLugar.text.toString()
        val fecha = binding.etFecha.text.toString()
        val dias = binding.etDias.text.toString().toInt()
        val total = binding.etTotal.text.toString().toInt()
        if(lugar.isEmpty()){
            Toast.makeText(requireContext(),getString(R.string.msg_data),Toast.LENGTH_LONG).show()
        }
        else if(fecha.isEmpty()){
            Toast.makeText(requireContext(),getString(R.string.msg_data),Toast.LENGTH_LONG).show()
        }
        else{
            val reserva = Reserva(args.reserva.id,lugar,fecha,dias,total)
            reservaViewModel.updateReserva(reserva)
            Toast.makeText(requireContext(),getString(R.string.msg_reserva_updated),Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateReservaFragment_to_nav_reserva)
        }
    }

    private fun deleteReserva(){
        val builder=AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.si)){_,_ ->
            reservaViewModel.deleteReserva(args.reserva)
            Toast.makeText(requireContext(),
                getString(R.string.deleted)+" ${args.reserva.lugar}!",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateReservaFragment_to_nav_reserva)
        }
        builder.setNegativeButton(getString(R.string.no)) {_,_->}
        builder.setTitle(R.string.deleted)
        builder.setMessage(getString(R.string.seguroBorrar)+" ${args.reserva.lugar}?")
        builder.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //si es borrado...
        if(item.itemId==R.id.menu_delete){
            deleteReserva()
        }
        return super.onOptionsItemSelected(item)
    }
}