package com.example.proyecto.ui.hotel

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentUpdateHotelBinding
import com.example.proyecto.databinding.FragmentUpdateRestauranteBinding
import com.example.proyecto.model.Hotel
import com.example.proyecto.model.Reserva
import com.example.proyecto.model.Restaurante
import com.example.proyecto.viewmodel.HotelViewModel
import com.example.proyecto.viewmodel.ReservaViewModel
import com.example.proyecto.viewmodel.RestauranteViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UpdateHotelFragment : Fragment() {

    //recupera argumentos
    private val args by navArgs<UpdateHotelFragmentArgs>()

    private var _binding: FragmentUpdateHotelBinding? = null
    private val binding get() = _binding!!
    private lateinit var hotelViewModel: HotelViewModel

    private lateinit var reservaViewModel: ReservaViewModel //queeeeeeeeeeeeeeeeee

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hotelViewModel = ViewModelProvider(this).get(HotelViewModel :: class.java)
        reservaViewModel = ViewModelProvider(this).get(ReservaViewModel::class.java) //queeeeeeeeeeeeeeeee
        _binding = FragmentUpdateHotelBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment

        //cargar los valores edit
        binding.etNombre.setText(args.hotel.nombre)
        binding.etLocalizacion.setText(args.hotel.localizacion)
        binding.etPrecio.setText(args.hotel.precio)
        binding.etTelefono.setText(args.hotel.telefono)

        binding.btUpdate.setOnClickListener{ updateHotel() }
        binding.btReservar.setOnClickListener{ addReserva() } // queeeeeeeeeeeeeeeeeee
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun updateHotel(){
        val nombre = binding.etNombre.text.toString()
        val localizacion = binding.etLocalizacion.text.toString()
        val cocina = binding.etPrecio.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val usuario = Firebase.auth.currentUser?.email
        val codigoUsuario = "$usuario"
        if(nombre.isEmpty()){
            Toast.makeText(requireContext(),getString(R.string.msg_data),Toast.LENGTH_LONG).show()
        }
        else if(localizacion.isEmpty()){
            Toast.makeText(requireContext(),getString(R.string.msg_data),Toast.LENGTH_LONG).show()
        }
        else{
            if(args.hotel.creador.equals(codigoUsuario)){
                val hotel = Hotel(args.hotel.id,nombre,localizacion,cocina,telefono,codigoUsuario)
                hotelViewModel.updateHotel(hotel)
                Toast.makeText(requireContext(),getString(R.string.msg_restaurante_updated),Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_updateHotelFragment_to_nav_hotel)
            }else{
                Toast.makeText(requireContext(),getString(R.string.msg_nocreador),Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun addReserva() { //queeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee

        val nombre = binding.etNombre.text.toString()
        val fecha = binding.etFecha.text.toString()
        val dias = binding.etDias.text.toString().toInt()
        val precio = binding.etPrecio.text.toString().toInt()
        val total = dias * precio

        if(fecha.isNotEmpty()){
            val reserva = Reserva("",nombre,fecha,dias,total)
            reservaViewModel.addReserva(reserva)
            Toast.makeText(requireContext(),getString(R.string.msg_reserva_added),
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateHotelFragment_to_nav_hotel)
        }else {  //No hay info del reserva...
            Toast.makeText(requireContext(),getString(R.string.msg_data),
                Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteHotel(){
        val builder=AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.si)){_,_ ->
            hotelViewModel.deleteHotel(args.hotel)
            Toast.makeText(requireContext(),
                getString(R.string.deleted)+" ${args.hotel.nombre}!",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateHotelFragment_to_nav_hotel)
        }
        builder.setNegativeButton(getString(R.string.no)) {_,_->}
        builder.setTitle(R.string.deleted)
        builder.setMessage(getString(R.string.seguroBorrar)+" ${args.hotel.nombre}?")
        builder.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //si es borrado...
        if(item.itemId==R.id.menu_delete){
            deleteHotel()
        }
        return super.onOptionsItemSelected(item)
    }
}