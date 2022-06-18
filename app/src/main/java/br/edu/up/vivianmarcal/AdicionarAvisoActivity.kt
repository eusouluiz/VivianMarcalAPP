package br.edu.up.vivianmarcal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projeto_ds2.R
import com.google.android.material.textfield.TextInputEditText
import br.edu.up.vivianmarcal.model.aviso.Aviso
import android.content.Intent
import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import br.edu.up.vivianmarcal.adapter.MensagemListAdapter
import br.edu.up.vivianmarcal.firebase.FirebaseConstants
import br.edu.up.vivianmarcal.firebase.FirebaseVM
import br.edu.up.vivianmarcal.model.mensagem.Mensagem
import br.edu.up.vivianmarcal.model.mensagem.OrigemEnum
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import java.util.stream.IntStream

class AdicionarAvisoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("HH:mm")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_aviso)
        val editTextAviso = findViewById<TextInputEditText>(R.id.edit_text_aviso)
        if (intent.hasExtra("aviso")) {
            val aviso = intent
                .getSerializableExtra("aviso") as Aviso?
            editTextAviso.setText(aviso!!.texto)
        }
        val saveButton = findViewById<Button>(R.id.button_posta)
        saveButton.setOnClickListener { v: View? ->
            val aviso_completo = editTextAviso.text.toString()
            if (aviso_completo.isEmpty()) {
                editTextAviso.error = "Favor inserir o aviso"
                return@setOnClickListener
            }
            val aviso = Aviso(aviso_completo, sdf.format(calendar.time))
            val intent = Intent()
            intent.putExtra("aviso", aviso)
            setResult(RESULT_OK, intent)
            onBackPressed()
        }
        val cancelButton = findViewById<Button>(R.id.button_cancel)
        cancelButton.setOnClickListener { v: View? ->
            setResult(RESULT_CANCELED)
            onBackPressed()
        }


    }

}