package com.example.finance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.finance.database.FinanceUserCRUD
import com.example.finance.model.financeUserModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    // llamada a los componentes
    private lateinit var user: TextInputLayout
    private lateinit var username: TextInputEditText
    private lateinit var password:  TextInputLayout
    private lateinit var contrasenia: TextInputEditText
    private lateinit var register: TextView

    private lateinit var bLog: Button

    private lateinit var crud: FinanceUserCRUD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ESTE CODIGO OMITE EL MODO OSCURO EN LA APLICACION
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        //ESTE CODIGO MANTIENE LA PANTALLA ENCENDIDA MIENTRAS ESTE ABIERTA LA APLICACION
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        crud = FinanceUserCRUD(this)

        initView()

        register()

        bLog.setOnClickListener(){
            login()
        }
    }

    private fun initView(){
        //material design
        user = findViewById(R.id.user)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        contrasenia = findViewById(R.id.contrasenia)
        bLog = findViewById(R.id.bLog)
        register = findViewById(R.id.register)
    }

    private fun login(){
        // variables que se reciben del layout
        val user = username.text.toString().trim()
        val contrasenia = contrasenia.text.toString().trim()

        val financeUser = getFinanceUser(user, contrasenia)

        if(financeUser){

            val userData = crud.selectUser(user, contrasenia) // call to data
            val userSP = userData?.user.toString().trim()   // array bidimensional (usuario/contrasenia)
            val passwordSP = userData?.password.toString().trim()

            if(userSP == user && passwordSP == contrasenia){
                //evaluamos los datos registrados en bd sean iguales a los insertados en layout
                // navigation between interfaces
                val intent = Intent(this, SplashActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                //mensaje de contrasenias errada
                Toast.makeText(this,"Usuario o contraseña equivocada", Toast.LENGTH_SHORT).show()
            }
        }else{
            //mensaje de usuario no registrado
            Toast.makeText(this,"Usuario no registrado", Toast.LENGTH_SHORT).show()
        }

    }

    private fun getFinanceUser(user: String, password: String):Boolean{
    //Obtener datos de usuario,valida la bd
        val financeUser: financeUserModel? = crud.selectUser(user, password)
        return financeUser?.equals(null) != true

    }

    private fun register(){
    // redirection to form register
        register.setOnClickListener(){
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
            finish()
        }

    }
}