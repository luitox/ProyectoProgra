package com.example.proyecto.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.proyecto.data.ReservaDao
import com.example.proyecto.model.Reserva
import com.example.proyecto.repository.ReservaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReservaViewModel(application: Application)
    : AndroidViewModel(application){

    val getAllData : MutableLiveData<List<Reserva>>

    private val repository: ReservaRepository = ReservaRepository(ReservaDao())

    init {
        getAllData = repository.getAllData
    }

    fun addReserva(reserva: Reserva) {
        repository.addReserva(reserva)
    }

    fun updateReserva(reserva: Reserva) {
        repository.updateReserva(reserva)
    }

    fun deleteReserva(reserva: Reserva) {
        repository.deleteReserva(reserva)
    }



}