package com.example.proyecto.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.proyecto.model.Reserva
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
//import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.ktx.Firebase


class ReservaDao {

    //Firebase var
    private var codigoUsuario: String
    private var firestore: FirebaseFirestore

    init{
        val usuario = Firebase.auth.currentUser?.email
        codigoUsuario = "$usuario"
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun saveReserva(reserva: Reserva){
        val document: DocumentReference
        if(reserva.id.isEmpty()){
            //agregar
            document = firestore.
            collection("reservasApp").
            document(codigoUsuario).
            collection("misReservas").
            document()
            reserva.id = document.id
        }else{
            //modificar
            document = firestore.
            collection("reservasApp").
            document(codigoUsuario).
            collection("misReservas").
            document(reserva.id)
        }
        val set = document.set(reserva)
        set.addOnSuccessListener{
            Log.d("saveReserva","Guardado con exito")
        }
            .addOnCanceledListener {
                Log.e("saveReserva","Error al guardar")
            }
    }

    fun deleteReserva(reserva: Reserva){
        if(reserva.id.isNotEmpty()){
            firestore.
            collection("reservasApp").
            document(codigoUsuario).
            collection("misReservas").
            document(reserva.id).
            delete()
                .addOnSuccessListener{
                    Log.d("deleteReserva","Eliminado con exito")
                }
                .addOnCanceledListener {
                    Log.d("deleteReserva","Error al eliminar")
                }
        }
    }

    fun getReservas() : MutableLiveData<List<Reserva>> {
        val listaReservas = MutableLiveData<List<Reserva>>()
        firestore.
        collection("reservasApp").
        document(codigoUsuario).
        collection("misReservas").
        addSnapshotListener{ snapshot, e ->
            if(e != null){
                return@addSnapshotListener
            }
            if(snapshot != null) {
                val lista = ArrayList<Reserva>()
                val reservas = snapshot.documents
                reservas.forEach {
                    val reserva = it.toObject(Reserva ::class.java)
                    if (reserva!=null) {
                        lista.add(reserva)
                    }
                }
                listaReservas.value = lista
            }
        }
        return listaReservas
    }
}