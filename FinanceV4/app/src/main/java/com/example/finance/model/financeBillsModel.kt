package com.example.finance.model

class financeBillsModel (idBills: Int?, billsType: String, cant: Float?, dateBills: String?, codDoc: String?){

    var idBills: Int? = null
    var billsType: String? = null
    var cant: Float? = null
    var dateBills: String? = null
    var codDoc: String? = null

    init {
        this.idBills = idBills
        this.billsType = billsType
        this.cant = cant
        this.dateBills =  dateBills
        this.codDoc = codDoc
    }
}