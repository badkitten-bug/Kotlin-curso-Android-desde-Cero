package com.example.finance.model

class financeTypeModel (idType: Int?, detalle: String?, estado: String?){

    var idType: Int? = null
    var detalle: String? = null
    var estado: String? = null

    init {
        this.idType = idType
        this.detalle = detalle
        this.estado = estado
    }
}