package com.example.proyecto.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.proyecto.model.Hotel
import com.example.proyecto.model.Restaurante
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
//import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.ktx.Firebase
import io.grpc.InternalChannelz.id


class HotelDao {

    //Firebase var
    private var codigoUsuario: String
    private var firestore: FirebaseFirestore

    init{
        val usuario = Firebase.auth.currentUser?.email
        codigoUsuario = "$usuario"
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun saveHotel(hotel: Hotel){
        val document: DocumentReference
        if(hotel.id.isEmpty()){
            //agregar
            document = firestore.
            collection("hotelesApp").
            document(codigoUsuario).
            collection("misHoteles").
            document()
            hotel.id = document.id
        }else{
            //modificar
            document = firestore.
            collection("hotelesApp").
            document(codigoUsuario).
            collection("misHoteles").
            document(hotel.id)
        }
        val set = document.set(hotel)
        set.addOnSuccessListener{
            Log.d("saveHotel","Guardado con exito")
        }
            .addOnCanceledListener {
                Log.e("saveHotel","Error al guardar")
            }
    }

    fun deleteHotel(hotel: Hotel){
        if(hotel.id.isNotEmpty()){
            firestore.
            collection("hotelesApp").
            document(codigoUsuario).
            collection("misHoteles").
            document(hotel.id).
            delete()
                .addOnSuccessListener{
                    Log.d("deleteHotel","Eliminado con exito")
                }
                .addOnCanceledListener {
                    Log.d("deleteHotel","Error al eliminar")
                }
        }
    }

    fun getHotel() : MutableLiveData<List<Hotel>> {
        val listaHoteles = MutableLiveData<List<Hotel>>()
        firestore.
        collection("hotelesApp").
        document(codigoUsuario).
        collection("misHoteles").
        addSnapshotListener{ snapshot, e ->
            if(e != null){
                return@addSnapshotListener
            }
            if(snapshot != null) {
                val lista = ArrayList<Hotel>()
                val hoteles = snapshot.documents
                hoteles.forEach {
                    val hotel = it.toObject(Hotel ::class.java)
                    if (hotel!=null) {
                        lista.add(hotel)
                    }
                }
                listaHoteles.value = lista
            }
        }
        return listaHoteles
    }
}