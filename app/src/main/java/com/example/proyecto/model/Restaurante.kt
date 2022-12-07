package com.example.proyecto.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
//@Entity(tableName = "restaurante")

data class Restaurante(
    //Clase que mapea una tabla de sqlite
    //@PrimaryKey(autoGenerate = true)
    var id: String,
    //@ColumnInfo(name="nombre")
    val nombre: String,
    //@ColumnInfo(name="correo")
    val localizacion: String,
    //@ColumnInfo(name="web")
    val cocina: String,
    //@ColumnInfo(name="telefono")
    val telefono: String?
) : Parcelable{
    constructor():
            this("","","","","")
}