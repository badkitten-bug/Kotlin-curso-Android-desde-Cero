package com.example.finance

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finance.database.BillsCRUD
import com.example.finance.model.financeBillsModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import java.util.*

class Bills : AppCompatActivity() {

    private lateinit var spTipo: Spinner
    private lateinit var tvCantidadIngreso: TextInputLayout
    private lateinit var etCantidadIngreso: TextInputEditText
    private lateinit var imgCalendar: ImageView
    private lateinit var tvFechaIngreso: TextInputLayout
    private lateinit var etFechaIngreso: TextInputEditText
    private lateinit var imgScan: ImageView
    private lateinit var tvScan: TextInputLayout
    private lateinit var etScan: TextInputEditText
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

        imgScan.setOnClickListener{
            initScanner()
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
        imgScan = findViewById(R.id.imgScan)
        tvScan = findViewById(R.id.tvScan)
        etScan = findViewById(R.id.etScan)

        etFechaIngreso.isEnabled = false
    }

    private fun initSpinnerBills(context: Context){
        val lisType = arrayOf("Gasto Hormiga", "Gastos Generales")
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

    private fun registerBills(){
        val tipo = spTipo.selectedItem.toString().trim()
        val cantidadIngreso = etCantidadIngreso.text.toString().toFloat()
        val fechaIngreso = etFechaIngreso.text.toString().trim()
        val codDoc = etScan.text.toString().trim()

        val status = crud.insertBills(financeBillsModel(idBills = null, billsType = tipo, cant = cantidadIngreso, dateBills = fechaIngreso, codDoc = codDoc))
        if(status > -1){
            Toast.makeText(this,"Ingreso agregado", Toast.LENGTH_SHORT).show()

            val intent = Intent( this,Control::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun initScanner(){
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setTorchEnabled(false)
        integrator.setBeepEnabled(true)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if(result.contents == null){
            Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
        }else{
            etScan.setText(result.contents)
        }
    }
}