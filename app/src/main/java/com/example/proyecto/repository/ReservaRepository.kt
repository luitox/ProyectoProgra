package com.example.proyecto.repository

import androidx.lifecycle.MutableLiveData
import com.example.proyecto.data.ReservaDao
import com.example.proyecto.model.Reserva

class ReservaRepository (private val reservaDao: ReservaDao) {
    val getAllData: MutableLiveData<List<Reserva>> = reservaDao.getReservas()

    fun addReserva(reserva: Reserva) {
        reservaDao.saveReserva(reserva)
    }

    fun updateReserva(reserva: Reserva) {
        reservaDao.saveReserva(reserva)
    }

    fun deleteReserva(reserva: Reserva) {
        reservaDao.deleteReserva(reserva)
    }

}