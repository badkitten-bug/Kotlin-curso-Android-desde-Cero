package com.example.finance.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.finance.model.financeTypeModel

class FinanceTypeCRUD(context: Context) {

    private var helper:SQLiteOpenHelper? = null

    init {
        helper = SQLiteOpenHelper(context)

    }

    fun insertType(type: financeTypeModel): Long{
        val db:SQLiteDatabase = helper?.writableDatabase!!

        val contentValues = ContentValues()
        contentValues.put(FinanceContract.Companion.SalaryType.idType,type.idType )
        contentValues.put(FinanceContract.Companion.SalaryType.detailFinance,type.detalle )
        contentValues.put(FinanceContract.Companion.SalaryType.statusFinance,type.estado )

        val success = db.insert(FinanceContract.Companion.SalaryType.TBLTYPE,null, contentValues)
        db.close()

        return success
    }

    fun selectType():financeTypeModel?{
        var item: financeTypeModel? = null

        val db: SQLiteDatabase = helper?.readableDatabase!!

        val columnas = arrayOf(
            FinanceContract.Companion.SalaryType.idType,
            FinanceContract.Companion.SalaryType.detailFinance,
            FinanceContract.Companion.SalaryType.statusFinance,
        )

        val c: Cursor = db.query(
            FinanceContract.Companion.SalaryType.TBLTYPE,
            columnas,
            "status = 1",
            null,
            null,
            null,
            null,
        )
        while (c.moveToNext()){
            item =
                financeTypeModel(
                    c.getInt(c.getColumnIndexOrThrow(FinanceContract.Companion.SalaryType.idType)),
                    c.getString(c.getColumnIndexOrThrow(FinanceContract.Companion.SalaryType.detailFinance)),
                    c.getString(c.getColumnIndexOrThrow(FinanceContract.Companion.SalaryType.statusFinance))
                )
        }
        c.close()

        return item
    }
}
