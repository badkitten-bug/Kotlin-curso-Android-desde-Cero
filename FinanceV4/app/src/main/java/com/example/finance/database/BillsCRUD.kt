package com.example.finance.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.airbnb.lottie.animation.content.Content
import com.example.finance.Bills
import com.example.finance.model.financeBillsModel

class BillsCRUD(context: Context) {
    private var helper:SQLiteOpenHelper? = null

    init {
        helper = SQLiteOpenHelper(context)
    }

    fun insertBills(bills: financeBillsModel): Long {
        val db: SQLiteDatabase =helper?.writableDatabase!!

        val contentValues = ContentValues()

        val success = db.insert(FinanceContract.Companion.Salary.TBLSALARY,null,contentValues)
        db.close()

        return success
    }
}
