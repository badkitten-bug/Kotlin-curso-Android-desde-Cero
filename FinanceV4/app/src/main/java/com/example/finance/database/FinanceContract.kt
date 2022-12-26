package com.example.finance.database

import android.provider.BaseColumns

class FinanceContract {
//creacion de constantes para la tabla
    companion object{
        const val DATABASE_VERSION = 1
        class FinanceUsers: BaseColumns{
            companion object{
                const val TBLUSER = "financeUser"
                const val user = "user"
                const val password = "password"
            }
        }
    }
}