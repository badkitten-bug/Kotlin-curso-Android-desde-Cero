package com.example.finance

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView

class Control : AppCompatActivity() {

    private lateinit var cardSalary: CardView
    private lateinit var cardBills: CardView
    private lateinit var cardSavings: CardView
    private lateinit var tSalary: TextView
    private lateinit var tSalary1: TextView
    private lateinit var vSalary: Button

    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control)

        supportActionBar?.hide()

        //ESTE CODIGO OMITE EL MODO OSCURO EN LA APLICACION
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        //ESTE CODIGO MANTIENE LA PANTALLA ENCENDIDA MIENTRAS ESTE ABIERTA LA APLICACION
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        initToolbar()

        initView()

        viewSalary()

        viewBills()
    }


    fun initToolbar(){
        //barra para financeControl
        toolbar = findViewById(R.id.my_toolbar)
        toolbar?.setLogo(R.mipmap.ic_launcher_foreground)
        supportActionBar?.setDisplayUseLogoEnabled(false)
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_principal, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // los botones obtienen funcion
        when(item.itemId){

            R.id.icon_logout -> {
                logout()
                return true

            }

            else -> {return super.onOptionsItemSelected(item)}

        }
        return super.onOptionsItemSelected(item)
    }



    private fun viewSalary(){
        //funcion para redirigirme a la Salary
        cardSalary.setOnClickListener(){
            val intent = Intent(this, Salary::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun viewBills(){
        cardBills.setOnClickListener(){
            val intent = Intent(this, Bills::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initView(){
        //initializer views
        cardSalary = findViewById(R.id.cardSalary)
        cardBills = findViewById(R.id.cardBills)
        cardSavings = findViewById(R.id.cardSavings)
    }

    private fun logout(){
        val sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE)
        val savedString = sharedPref.edit().remove("USER_KEY").commit()
        if (savedString){
            val intent = Intent(this, MainActivity()::class.java)
            startActivity(intent)
            finish()
        }
    }


}