package com.example.finance.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.finance.model.FinanceSalaryModel

class SalaryCRUD(context: Context) {
    private var helper:SQLiteOpenHelper? = null

    init {
        helper = SQLiteOpenHelper(context)
    }

    fun insertSalary(salary: FinanceSalaryModel): Long{
        val db:SQLiteDatabase =helper?.writableDatabase!!

        val contentValues = ContentValues()
        contentValues.put(FinanceContract.Companion.Salary.idSalary,salary.idSalary)
        contentValues.put(FinanceContract.Companion.Salary.salaryType,salary.salaryType)
        contentValues.put(FinanceContract.Companion.Salary.cant,salary.cant)
        contentValues.put(FinanceContract.Companion.Salary.dateSalary, salary.dateSalary)


        val success = db.insert(FinanceContract.Companion.Salary.TBLSALARY,null,contentValues)
        db.close()

        return success

    }

    fun selectType():FinanceSalaryModel?{
        var item: FinanceSalaryModel? = null

        val db: SQLiteDatabase = helper?.readableDatabase!!

        val columnas = arrayOf(
            FinanceContract.Companion.Salary.idSalary,
            FinanceContract.Companion.Salary.salaryType,
            FinanceContract.Companion.Salary.cant,
            FinanceContract.Companion.Salary.dateSalary
        )
        val c: Cursor = db.query(
            FinanceContract.Companion.Salary.TBLSALARY,
            columnas,
            "status = 1",
            null,
            null,
            null,
            null,
        )
        while (c.moveToNext()){
            item =
                FinanceSalaryModel(
                    c.getInt(c.getColumnIndexOrThrow(FinanceContract.Companion.Salary.idSalary)),
                    c.getString(c.getColumnIndexOrThrow(FinanceContract.Companion.Salary.salaryType)),
                    c.getFloat(c.getColumnIndexOrThrow(FinanceContract.Companion.Salary.cant)),
                    c.getString(c.getColumnIndexOrThrow(FinanceContract.Companion.Salary.dateSalary)),
                )
        }
        c.close()
        return item
    }

}