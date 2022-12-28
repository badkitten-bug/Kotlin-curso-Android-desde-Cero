package com.example.finance.model

import com.example.finance.database.FinanceContract

class financeSalaryModel (idSalary: Int?, salaryType: String, cant: Float?, dateSalary: String?) {

    var idSalary: Int? = null
    var salaryType: String? = null
    var cant: Float? = null
    var dateSalary: String? = null

    init {
        this.idSalary = idSalary
        this.salaryType = salaryType
        this.cant = cant
        this.dateSalary = dateSalary
    }
}