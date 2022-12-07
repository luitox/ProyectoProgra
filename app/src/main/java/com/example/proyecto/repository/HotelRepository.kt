package com.example.proyecto.repository

import androidx.lifecycle.MutableLiveData
import com.example.proyecto.data.HotelDao
import com.example.proyecto.data.RestauranteDao
import com.example.proyecto.model.Hotel
import com.example.proyecto.model.Restaurante

class HotelRepository (private val HotelDao: HotelDao) {
    val getAllData: MutableLiveData<List<Hotel>> = HotelDao.getHotel()

    fun addHotel(hotel: Hotel) {
        HotelDao.saveHotel(hotel)
    }

    fun updateHotel(hotel: Hotel) {
        HotelDao.saveHotel(hotel)
    }

    fun deleteHotel(hotel: Hotel) {
        HotelDao.deleteHotel(hotel)
    }

}