package com.example.proyecto.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.proyecto.data.RestauranteDao
import com.example.proyecto.model.Restaurante
import com.example.proyecto.repository.RestauranteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestauranteViewModel(application: Application)
    : AndroidViewModel(application){

    val getAllData : MutableLiveData<List<Restaurante>>

    private val repository: RestauranteRepository = RestauranteRepository(RestauranteDao())

    init {
        getAllData = repository.getAllData
    }

    fun addRestaurante(restaurante: Restaurante) {
        repository.addRestaurante(restaurante)
    }

    fun updateRestaurante(restaurante: Restaurante) {
        repository.updateRestaurante(restaurante)
    }

    fun deleteRestaurante(restaurante: Restaurante) {
        repository.deleteRestaurante(restaurante)
    }



}