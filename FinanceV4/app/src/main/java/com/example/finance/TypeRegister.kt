package com.example.finance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.finance.database.FinanceTypeCRUD
import com.example.finance.model.financeTypeModel
import com.example.finance.model.financeUserModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class TypeRegister : AppCompatActivity() {

    private lateinit var tvDetalleTipo: TextInputLayout
    private lateinit var etDetalleTipo: TextInputEditText
    private lateinit var btnType: MaterialButton

    private lateinit var crud: FinanceTypeCRUD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_register)

        crud = FinanceTypeCRUD(this)

        initView()

        btnType.setOnClickListener(){
            saveType()
        }

    }


    private fun initView(){
        tvDetalleTipo = findViewById(R.id.tvDetalleTipo)
        etDetalleTipo = findViewById(R.id.etDetalleTipo)
        btnType = findViewById(R.id.btnType)
    }

    private fun saveType(){

        val detalle = etDetalleTipo.text.toString().trim()

        val status = crud.insertType(financeTypeModel(idType = null, detalle = detalle, estado = "1"))

        if(status > -1){
            Toast.makeText(this, "Detalle Agregado", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, Salary::class.java)
            startActivity(intent)
            finish()
        }
    }

}