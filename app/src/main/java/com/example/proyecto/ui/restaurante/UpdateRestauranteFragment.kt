package com.example.proyecto.ui.restaurante

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentUpdateRestauranteBinding
import com.example.proyecto.model.Reserva
import com.example.proyecto.model.Restaurante
import com.example.proyecto.viewmodel.ReservaViewModel
import com.example.proyecto.viewmodel.RestauranteViewModel

class UpdateRestauranteFragment : Fragment() {

    //recupera argumentos
    private val args by navArgs<UpdateRestauranteFragmentArgs>()

    private var _binding: FragmentUpdateRestauranteBinding? = null
    private val binding get() = _binding!!
    private lateinit var restauranteViewModel: RestauranteViewModel

    private lateinit var reservaViewModel: ReservaViewModel //queeeeeeeeeeeeeeeeee

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        restauranteViewModel = ViewModelProvider(this).get(RestauranteViewModel :: class.java)
        reservaViewModel = ViewModelProvider(this).get(ReservaViewModel::class.java) //queeeeeeeeeeeeeeeee

        _binding = FragmentUpdateRestauranteBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment

        //cargar los valores edit
        binding.etNombre.setText(args.restaurante.nombre)
        binding.etLocalizacion.setText(args.restaurante.localizacion)
        binding.etCocina.setText(args.restaurante.cocina)
        binding.etTelefono.setText(args.restaurante.telefono)

        binding.btUpdate.setOnClickListener{ updateRestaurante() }
        binding.btReservar.setOnClickListener{ addReserva() } // queeeeeeeeeeeeeeeeeee
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun updateRestaurante(){
        val nombre = binding.etNombre.text.toString()
        val localizacion = binding.etLocalizacion.text.toString()
        val cocina = binding.etCocina.text.toString()
        val telefono = binding.etTelefono.text.toString()
        if(nombre.isEmpty()){
            Toast.makeText(requireContext(),getString(R.string.msg_data),Toast.LENGTH_LONG).show()
        }
        else if(localizacion.isEmpty()){
            Toast.makeText(requireContext(),getString(R.string.msg_data),Toast.LENGTH_LONG).show()
        }
        else{
            val restaurante = Restaurante(args.restaurante.id,nombre,localizacion,cocina,telefono)
            restauranteViewModel.updateRestaurante(restaurante)
            Toast.makeText(requireContext(),getString(R.string.msg_restaurante_updated),Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateRestauranteFragment_to_nav_restaurante)
        }
    }

    private fun addReserva() { //queeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee

        val nombre = binding.etNombre.text.toString()
        val fecha = binding.etFecha.text.toString()


        if(fecha.isNotEmpty()){
            val reserva = Reserva("",nombre,fecha,1,null)
            reservaViewModel.addReserva(reserva)
            Toast.makeText(requireContext(),getString(R.string.msg_reserva_added),
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateRestauranteFragment_to_nav_restaurante)
        }else {  //No hay info del reserva...
            Toast.makeText(requireContext(),getString(R.string.msg_data),
                Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteRestaurante(){
        val builder=AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.si)){_,_ ->
            restauranteViewModel.deleteRestaurante(args.restaurante)
            Toast.makeText(requireContext(),
                getString(R.string.deleted)+" ${args.restaurante.nombre}!",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateRestauranteFragment_to_nav_restaurante)
        }
        builder.setNegativeButton(getString(R.string.no)) {_,_->}
        builder.setTitle(R.string.deleted)
        builder.setMessage(getString(R.string.seguroBorrar)+" ${args.restaurante.nombre}?")
        builder.create().show()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //si es borrado...
        if(item.itemId==R.id.menu_delete){
            deleteRestaurante()
        }
        return super.onOptionsItemSelected(item)
    }
}