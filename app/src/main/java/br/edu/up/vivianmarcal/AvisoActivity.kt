package br.edu.up.vivianmarcal

import androidx.appcompat.app.AppCompatActivity
import br.edu.up.vivianmarcal.model.aviso.Aviso
import br.edu.up.vivianmarcal.adapter.AvisoListAdapter
import android.os.Bundle
import com.example.projeto_ds2.R
import androidx.recyclerview.widget.RecyclerView
import br.edu.up.vivianmarcal.adapter.AvisoListAdapter.OnAvisoClickListener
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.Intent
import br.edu.up.vivianmarcal.AdicionarAvisoActivity
import android.app.Activity
import android.util.Log
import android.widget.Button
import br.edu.up.vivianmarcal.model.usuario.Usuario
import java.util.ArrayList

class AvisoActivity : AppCompatActivity() {
    private val avisos = ArrayList<Aviso?>()
    private var usuario: Usuario? = null
    var avisoListAdapter: AvisoListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.hasExtra("Usuario")){
            Log.v("App", "LOG: resgatando usuario")
            usuario = intent.getSerializableExtra("Usuario") as Usuario?

            Log.v("App", "LOG: tipoUsuario: " + usuario!!.tipoUsuario)
        }else{
            Log.v("App", "ERRO: usuario nao enviado!")
            finish()
        }

        setContentView(R.layout.activity_aviso)
        val buttonAdicionar = findViewById<Button>(R.id.button_add)
        buttonAdicionar.setOnClickListener { callRegisterActivity(null) }
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_avisos)
        avisoListAdapter = AvisoListAdapter(
            avisos
        ) { aviso -> callRegisterActivity(aviso) }
        recyclerView.adapter = avisoListAdapter
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
    }

    fun callRegisterActivity(aviso: Aviso?) {
        val intent = Intent(
            this@AvisoActivity,
            AdicionarAvisoActivity::class.java
        )
        if (aviso != null) {
            intent.putExtra("aviso", aviso)
            avisos.remove(aviso)
        }
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val aviso = data!!.getSerializableExtra("aviso") as Aviso?
            avisos.add(aviso)
            avisoListAdapter!!.notifyDataSetChanged()
        }
        if (requestCode == 100 && resultCode == RESULT_CANCELED) {
            avisoListAdapter!!.notifyDataSetChanged()
        }
    }
}