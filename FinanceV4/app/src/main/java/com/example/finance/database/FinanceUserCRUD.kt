package com.example.finance.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.finance.model.financeUserModel

class FinanceUserCRUD(context: Context) {

        private var helper:SQLiteOpenHelper? = null

    init {

        helper = SQLiteOpenHelper(context)

    }

    fun insertUser(pre: financeUserModel): Long {
        val db:SQLiteDatabase = helper?.writableDatabase!!

        val contentValues = ContentValues()
        contentValues.put(FinanceContract.Companion.FinanceUsers.user, pre.user)
        contentValues.put(FinanceContract.Companion.FinanceUsers.password, pre.password)

        val success = db.insert(FinanceContract.Companion.FinanceUsers.TBLUSER, null, contentValues)
        db.close()

        return success

    }

    fun selectUser(user: String?, password: String?): financeUserModel?{
        var item: financeUserModel? = null

        val db: SQLiteDatabase = helper?.readableDatabase!!

        val columnas = arrayOf(

            FinanceContract.Companion.FinanceUsers.user,
            FinanceContract.Companion.FinanceUsers.password,

        )

        val c: Cursor = db.query(
            FinanceContract.Companion.FinanceUsers.TBLUSER,
            columnas,
            "user = ? and password = ?",
            arrayOf(user, password),
            null,
            null,
            null

        )

        while (c.moveToNext()){
            item =
                financeUserModel(
                    c.getString(c.getColumnIndexOrThrow(FinanceContract.Companion.FinanceUsers.user)),
                    c.getString(c.getColumnIndexOrThrow(FinanceContract.Companion.FinanceUsers.password)))


        }

        c.close()

        return item

    }
}