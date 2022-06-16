package br.edu.up.vivianmarcal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.projeto_ds2.R
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.ToggleButton
import br.edu.up.vivianmarcal.model.usuario.TipoUsuario
import br.edu.up.vivianmarcal.model.usuario.Usuario

class MainActivity : AppCompatActivity() {

    private var usuario: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)

        if (usuario == null){
            Log.v("App", "LOG: setando usuario como PAI")
            usuario = Usuario(5678.toLong(), "nomeQualquer", TipoUsuario.PAI_ALUNO)
        }

        setContentView(R.layout.activity_main)
        val b = findViewById<Button>(R.id.button)
        b.setOnClickListener {
            val intent = Intent(this@MainActivity, MensagensActivity::class.java)
            intent.putExtra("Usuario", usuario)
            startActivity(intent)
        }
        val buttonAvisos = findViewById<Button>(R.id.button2)
        buttonAvisos.setOnClickListener {
            val intent = Intent(this@MainActivity, AvisoActivity::class.java)
            intent.putExtra("Usuario", usuario)
            startActivity(intent)
        }

        val buttonTrocaUsuario = findViewById<ToggleButton>(R.id.trocaUsuario)
        buttonTrocaUsuario.setOnCheckedChangeListener { _, isChecked ->
            try {
                if (isChecked){
                    Log.v("App", "LOG: isChecked")

                    usuario!!.id = 1234.toLong()
                    usuario!!.tipoUsuario = TipoUsuario.ESCOLA
                }else{
                    Log.v("App", "LOG: isNotChecked")

                    usuario!!.id = 5678.toLong()
                    usuario!!.tipoUsuario = TipoUsuario.PAI_ALUNO
                }
            } catch (e: Exception) {
                Log.v("App", "ERRO: " + e.message)
            }
        }

    }
}