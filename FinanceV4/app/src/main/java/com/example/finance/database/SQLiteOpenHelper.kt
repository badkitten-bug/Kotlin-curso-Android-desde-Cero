package com.example.finance.database

import android.content.Context
import com.example.finance.database.FinanceContract
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase

class SQLiteOpenHelper(context: Context): SQLiteOpenHelper(context, FinanceContract.Companion.FinanceUsers.TBLUSER, null, FinanceContract.DATABASE_VERSION ) {

companion object{

    const val createTblFinanceUser = "CREATE TABLE "+ FinanceContract.Companion.FinanceUsers.TBLUSER +
        " (" +FinanceContract.Companion.FinanceUsers.user + " TEXT, " +
            FinanceContract.Companion.FinanceUsers.password + " TEXT )"

    const val rmvTblFinanceUsers = " DROP TABLE IF EXIST " + FinanceContract.Companion.FinanceUsers.TBLUSER
}

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(createTblFinanceUser)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(rmvTblFinanceUsers)
    }
}