package com.example.proyecto.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
//@Entity(tableName = "hotel")

data class Hotel(
    //Clase que mapea una tabla de sqlite
    //@PrimaryKey(autoGenerate = true)
    var id: String,
    //@ColumnInfo(name="nombre")
    val nombre: String,
    //@ColumnInfo(name="correo")
    val localizacion: String,
    //@ColumnInfo(name="web")
    val precio: String,
    //@ColumnInfo(name="telefono")
    val telefono: String?,

    val creador: String?
) : Parcelable{
    constructor():
            this("","","","","","")
}