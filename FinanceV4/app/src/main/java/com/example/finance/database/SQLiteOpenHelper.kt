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

    const val createTblFinanceControlType = "CREATE TABLE "+ FinanceContract.Companion.SalaryType.TBLTYPE +
         " (" +FinanceContract.Companion.SalaryType.idType + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FinanceContract.Companion.SalaryType.detailFinance + " TEXT, " +
            FinanceContract.Companion.SalaryType. statusFinance + " TEXT )"

    const val rmvTblFinanceControlType = " DROP TABLE IF EXIST " + FinanceContract.Companion.SalaryType.TBLTYPE

    const val createTblFinanceSalary = "CREATE TABLE " + FinanceContract.Companion.Salary.TBLSALARY +
            " (" +FinanceContract.Companion.Salary.idSalary + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FinanceContract.Companion.Salary.salaryType + " TEXT, " +
                FinanceContract.Companion.Salary.cant + " INTEGER, " +
                FinanceContract.Companion.Salary.dateSalary + " TEXT )"

    const val rmvTblFinanceSalary = " DROP TABLE IF EXIST " + FinanceContract.Companion.Salary.TBLSALARY
}

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(createTblFinanceUser)
        db?.execSQL(createTblFinanceControlType)
        db?.execSQL(createTblFinanceSalary)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(rmvTblFinanceUsers)
        db!!.execSQL(rmvTblFinanceControlType)
        db!!.execSQL(rmvTblFinanceSalary)
    }
}