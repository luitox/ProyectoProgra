package com.example.proyecto.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.proyecto.data.HotelDao
import com.example.proyecto.data.RestauranteDao
import com.example.proyecto.model.Hotel
import com.example.proyecto.model.Restaurante
import com.example.proyecto.repository.HotelRepository
import com.example.proyecto.repository.RestauranteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HotelViewModel(application: Application)
    : AndroidViewModel(application){

    val getAllData : MutableLiveData<List<Hotel>>

    private val repository: HotelRepository = HotelRepository(HotelDao())

    init {
        getAllData = repository.getAllData
    }

    fun addHotel(hotel: Hotel) {
        repository.addHotel(hotel)
    }

    fun updateHotel(hotel: Hotel) {
        repository.updateHotel(hotel)
    }

    fun deleteHotel(hotel: Hotel) {
        repository.deleteHotel(hotel)
    }



}