package com.example.finance

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import com.example.finance.database.SalaryCRUD
import com.example.finance.model.FinanceSalaryModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class Salary : AppCompatActivity() {

    private lateinit var spTipo: Spinner
    private lateinit var tvCantidadIngreso: TextInputLayout
    private lateinit var etCantidadIngreso: TextInputEditText
    private lateinit var imgCalendar: ImageView
    private lateinit var tvFechaIngreso: TextInputLayout
    private lateinit var etFechaIngreso: TextInputEditText
    private lateinit var btnSalary: MaterialButton

    private lateinit var crud: SalaryCRUD


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salary)

        crud = SalaryCRUD( this)

        initView()

        initSpinnerSalary(this)

        openCalendar()

        btnSalary.setOnClickListener{
            registerSalary()
        }

    }

    private fun initView(){

        tvCantidadIngreso = findViewById(R.id.tvCantidadIngreso)
        etCantidadIngreso = findViewById(R.id.etCantidadIngreso)
        spTipo = findViewById(R.id.spTipo)
        imgCalendar = findViewById(R.id.imgCalendar)
        tvFechaIngreso = findViewById(R.id.tvFechaIngreso)
        etFechaIngreso = findViewById(R.id.etFechaIngreso)
        btnSalary = findViewById(R.id.btnSalary)

        etFechaIngreso.isEnabled = false

    }

    private fun initSpinnerSalary(context: Context){
        val lisType = arrayOf("Sueldo", "Empresa")
        val listSP = arrayListOf<String>()

        for(element in lisType){
            listSP.add(element)
        }


        val adaptador = ArrayAdapter(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listSP)
        spTipo.adapter = adaptador

    }

    @SuppressLint("SetTextI18n")
    private fun openCalendar(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        imgCalendar.setOnClickListener {
            DatePickerDialog(this, { _, myear, mmonth, mday ->

                etFechaIngreso.setText("$myear/${mmonth + 1}/$mday")

            }, year, month, day).show()

        }

    }

    private fun registerSalary(){
        val tipo = spTipo.selectedItem.toString().trim()
        val cantidadIngreso = etCantidadIngreso.text.toString().toFloat()
        val fechaIngreso = etFechaIngreso.text.toString().trim()

        val status = crud.insertSalary(FinanceSalaryModel(idSalary = null, salaryType = tipo, cant = cantidadIngreso, dateSalary = fechaIngreso))
        if(status > -1){
            Toast.makeText(this,"Ingreso agregado", Toast.LENGTH_SHORT).show()

            val intent = Intent( this,Control::class.java)
            startActivity(intent)
            finish()
        }
    }


}