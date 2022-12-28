package com.example.finance

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import com.example.finance.database.BillsCRUD
import com.example.finance.database.SalaryCRUD
import com.example.finance.model.financeBillsModel
import com.example.finance.model.financeSalaryModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class Bills : AppCompatActivity() {

    private lateinit var spTipo: Spinner
    private lateinit var tvCantidadIngreso: TextInputLayout
    private lateinit var etCantidadIngreso: TextInputEditText
    private lateinit var imgCalendar: ImageView
    private lateinit var tvFechaIngreso: TextInputLayout
    private lateinit var etFechaIngreso: TextInputEditText
    private lateinit var btnBills: MaterialButton

    private lateinit var crud: BillsCRUD


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bills)

        crud = BillsCRUD(this)

        initView()

        initSpinnerBills(this)

        openCalendar()

        btnBills.setOnClickListener{
            registerBills()
        }
    }

    private fun initView(){

        tvCantidadIngreso = findViewById(R.id.tvCantidadIngreso)
        etCantidadIngreso = findViewById(R.id.etCantidadIngreso)
        spTipo = findViewById(R.id.spTipo)
        imgCalendar = findViewById(R.id.imgCalendar)
        tvFechaIngreso = findViewById(R.id.tvFechaIngreso)
        etFechaIngreso = findViewById(R.id.etFechaIngreso)
        btnBills = findViewById(R.id.btnSalary)

        etFechaIngreso.isEnabled = false
    }

    private fun initSpinnerBills(context: Context){
        val lisType = arrayOf("Sueldo", "Empresa")
        val listSP = arrayListOf<String>()

        for(element in lisType){
            listSP.add(element)
        }


        val adaptador = ArrayAdapter(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listSP)
        spTipo.adapter = adaptador

    }
    private fun openCalendar(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        imgCalendar.setOnClickListener {
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view, myear, mmonth, mday ->

                etFechaIngreso.setText("$myear/${mmonth + 1}/$mday")

            }, year, month, day).show()

        }
    }

    private fun registerBills(){
        val tipo = spTipo.selectedItem.toString().trim()
        val cantidadIngreso = etCantidadIngreso.text.toString().toFloat()
        val fechaIngreso = etFechaIngreso.text.toString().trim()

        val status = crud.insertBills(financeBillsModel(idBills = null, billsType = tipo, cant = cantidadIngreso, dateBills = fechaIngreso))
        if(status > -1){
            Toast.makeText(this,"Ingreso agregado", Toast.LENGTH_SHORT).show()

            val intent = Intent( this,Control::class.java)
            startActivity(intent)
            finish()
        }

    }
}