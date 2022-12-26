package com.example.finance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide() //oculta la barra por defecto de android-studio

        CoroutineScope(Dispatchers.IO).launch {

            // tiempo en que la vista esta activa
            delay(2900L)

            startActivity(Intent(this@SplashActivity, Control::class.java))
            finish()

        }
    }
}