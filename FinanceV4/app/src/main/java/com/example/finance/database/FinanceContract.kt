package com.example.finance.database

import android.provider.BaseColumns

class FinanceContract {
//creacion de constantes para la tabla
    companion object{
        const val DATABASE_VERSION = 1
        class FinanceUsers: BaseColumns{
            companion object{
                const val TBLUSER = "financeControlUser"
                const val user = "user"
                const val password = "password"
            }
        }
        class SalaryType: BaseColumns {
            companion object{
                const val TBLTYPE = "salaryType"
                const val idType = "idType"
                const val detailFinance = "details"
                const val statusFinance = "status"
            }
        }

        class Salary: BaseColumns {
            companion object{
                const val TBLSALARY = "salary"
                const val idSalary = "idSalary"
                const val salaryType = "tipo"
                const val cant = "cantidad"
                const val dateSalary = "fecha"
            }
        }


    }
}