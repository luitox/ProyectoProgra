package com.example.proyecto.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.proyecto.model.Restaurante
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
//import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.ktx.Firebase


class RestauranteDao {

    //Firebase var
    private var codigoUsuario: String
    private var firestore: FirebaseFirestore

    init{
        val usuario = Firebase.auth.currentUser?.email
        codigoUsuario = "$usuario"
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun saveRestaurante(restaurante: Restaurante){
        val document: DocumentReference
        if(restaurante.id.isEmpty()){
            //agregar
            document = firestore.
            collection("restaurantesDB").
            document()
            restaurante.id = document.id
        }else{
            //modificar
            document = firestore.
            collection("restaurantesDB").
            document(restaurante.id)
        }
        val set = document.set(restaurante)
        set.addOnSuccessListener{
            Log.d("saveRestaurante","Guardado con exito")
        }
            .addOnCanceledListener {
                Log.e("saveRestaurante","Error al guardar")
            }
    }

    fun deleteRestaurante(restaurante: Restaurante){
        if(restaurante.id.isNotEmpty()){
            firestore.
            collection("restaurantesDB").
            document(restaurante.id).
            delete()
                .addOnSuccessListener{
                    Log.d("deleteRestaurante","Eliminado con exito")
                }
                .addOnCanceledListener {
                    Log.d("deleteRestaurante","Error al eliminar")
                }
        }
    }

    fun getRestaurantes() : MutableLiveData<List<Restaurante>> {
        val listaRestaurantes = MutableLiveData<List<Restaurante>>()
        firestore.
        collection("restaurantesDB").
        addSnapshotListener{ snapshot, e ->
            if(e != null){
                return@addSnapshotListener
            }
            if(snapshot != null) {
                val lista = ArrayList<Restaurante>()
                val restaurantes = snapshot.documents
                restaurantes.forEach {
                    val restaurante = it.toObject(Restaurante ::class.java)
                    if (restaurante!=null) {
                        lista.add(restaurante)
                    }
                }
                listaRestaurantes.value = lista
            }
        }
        return listaRestaurantes
    }
}