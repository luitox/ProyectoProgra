package com.example.proyecto.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
//@Entity(tableName = "restaurante")

data class Reserva(
    //Clase que mapea una tabla de sqlite
    //@PrimaryKey(autoGenerate = true)
    var id: String,
    //@ColumnInfo(name="nombre")
    val lugar: String,
    //@ColumnInfo(name="correo")
    val fecha: String,
    //@ColumnInfo(name="web")
    val dias: Int?,
    //@ColumnInfo(name="telefono")
    val total: Int?
) : Parcelable{
    constructor():
            this("","","",null,null)
}