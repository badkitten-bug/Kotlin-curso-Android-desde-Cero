package com.example.finance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.finance.database.FinanceUserCRUD
import com.example.finance.model.financeUserModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Register : AppCompatActivity() {

    private lateinit var user: TextInputLayout
    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputLayout
    private lateinit var contrasenia: TextInputEditText
    private lateinit var register: TextView

    private lateinit var bLog: Button

    private lateinit var crud: FinanceUserCRUD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        crud = FinanceUserCRUD(this)

        initView()

        bLog.setOnClickListener(){
            register()
        }

    }

    private fun initView(){
        user = findViewById(R.id.user)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        contrasenia = findViewById(R.id.contrasenia)
        bLog = findViewById(R.id.bLog)
        register = findViewById(R.id.tregiter)
    }

    private fun register(){

        val username = username.text.toString().trim()
        val password = contrasenia.text.toString().trim()

        val status = crud.insertUser(financeUserModel(user = username, password = password))

        if(status > -1){
            Toast.makeText(this, "Usuario Agregado", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}