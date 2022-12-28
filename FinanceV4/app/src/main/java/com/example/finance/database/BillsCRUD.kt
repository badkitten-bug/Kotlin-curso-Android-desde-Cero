package com.example.finance.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.finance.model.financeBillsModel

class BillsCRUD(context: Context) {
    private var helper:SQLiteOpenHelper? = null

    init {
        helper = SQLiteOpenHelper(context)
    }

    fun insertBills(bills: financeBillsModel): Long {
        val db: SQLiteDatabase =helper?.writableDatabase!!

        val contentValues = ContentValues()
        contentValues.put(FinanceContract.Companion.Bills.idBills,bills.idBills)
        contentValues.put(FinanceContract.Companion.Bills.billsType,bills.billsType)
        contentValues.put(FinanceContract.Companion.Bills.cant,bills.cant)
        contentValues.put(FinanceContract.Companion.Bills.dateBills, bills.dateBills)
        contentValues.put(FinanceContract.Companion.Bills.codDoc, bills.codDoc)


        val success = db.insert(FinanceContract.Companion.Bills.TBLBILLS,null,contentValues)
        db.close()

        return success
    }

    fun selectType(): financeBillsModel?{
        var item: financeBillsModel? = null

        val db: SQLiteDatabase = helper?.readableDatabase!!

        val columnas = arrayOf(
            FinanceContract.Companion.Bills.idBills,
            FinanceContract.Companion.Bills.billsType,
            FinanceContract.Companion.Bills.cant,
            FinanceContract.Companion.Bills.dateBills,
            FinanceContract.Companion.Bills.codDoc
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
                financeBillsModel(
                    c.getInt(c.getColumnIndexOrThrow(FinanceContract.Companion.Bills.idBills)),
                    c.getString(c.getColumnIndexOrThrow(FinanceContract.Companion.Bills.billsType)),
                    c.getFloat(c.getColumnIndexOrThrow(FinanceContract.Companion.Bills.cant)),
                    c.getString(c.getColumnIndexOrThrow(FinanceContract.Companion.Bills.dateBills)),
                    c.getString(c.getColumnIndexOrThrow(FinanceContract.Companion.Bills.codDoc))
                )
        }
        c.close()
        return item
    }

}
