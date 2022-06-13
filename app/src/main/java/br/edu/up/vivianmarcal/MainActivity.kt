package br.edu.up.vivianmarcal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.projeto_ds2.R
import android.content.Intent
import android.util.Log
import android.widget.Button
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val b = findViewById<Button>(R.id.button)
        b.setOnClickListener {
            val intent = Intent(this@MainActivity, MensagensActivity::class.java)
            startActivity(intent)
        }
        val buttonAvisos = findViewById<Button>(R.id.button2)
        buttonAvisos.setOnClickListener {
            val intent = Intent(this@MainActivity, AvisoActivity::class.java)
            startActivity(intent)
        }
    }
}