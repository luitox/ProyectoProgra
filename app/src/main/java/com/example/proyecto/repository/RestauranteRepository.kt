package com.example.proyecto.repository

import androidx.lifecycle.MutableLiveData
import com.example.proyecto.data.RestauranteDao
import com.example.proyecto.model.Restaurante

class RestauranteRepository (private val restauranteDao: RestauranteDao) {
    val getAllData: MutableLiveData<List<Restaurante>> = restauranteDao.getRestaurantes()

    fun addRestaurante(restaurante: Restaurante) {
        restauranteDao.saveRestaurante(restaurante)
    }

    fun updateRestaurante(restaurante: Restaurante) {
        restauranteDao.saveRestaurante(restaurante)
    }

    fun deleteRestaurante(restaurante: Restaurante) {
        restauranteDao.deleteRestaurante(restaurante)
    }

}