package br.edu.up.vivianmarcal

import androidx.appcompat.app.AppCompatActivity
import br.edu.up.vivianmarcal.model.aviso.Aviso
import br.edu.up.vivianmarcal.adapter.AvisoListAdapter
import android.os.Bundle
import com.example.projeto_ds2.R
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.Intent
import android.app.AlertDialog
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import br.edu.up.vivianmarcal.firebase.FirebaseConstants
import br.edu.up.vivianmarcal.firebase.FirebaseVM
import br.edu.up.vivianmarcal.model.usuario.TipoUsuario
import br.edu.up.vivianmarcal.model.usuario.Usuario
import com.google.firebase.Timestamp
import java.util.HashMap
import java.util.stream.IntStream.range
import kotlin.collections.ArrayList

@Suppress("CAST_NEVER_SUCCEEDS")
class AvisoActivity : AppCompatActivity() {
    private val avisos = ArrayList<Aviso?>()
    private var usuario: Usuario? = null
    var avisoListAdapter: AvisoListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.hasExtra("Usuario")) {
            Log.v("App", "LOG: resgatando usuario")
            usuario = intent.getSerializableExtra("Usuario") as Usuario?

            Log.v("App", "LOG: tipoUsuario: " + usuario!!.tipoUsuario)
        } else {
            Log.v("App", "ERRO: usuario nao enviado!")
            finish()
        }


        setContentView(R.layout.activity_aviso)
        val buttonAdicionar = findViewById<Button>(R.id.button_add)
        if (usuario!!.tipoUsuario == TipoUsuario.ESCOLA) {
            buttonAdicionar.setOnClickListener { callRegisterActivity(null) }
        }else{
            buttonAdicionar.visibility = View.GONE
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_avisos)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        defineAtualizarLista(recyclerView)
    }

    private fun callRegisterActivity(aviso: Aviso?) {
        if (aviso != null) {
            registerDialogStart(aviso)
        } else {
            registerDialogStart(null)
        }
    }

    private fun atualizarAvisosFB(aviso: Aviso, id: Int){
        FirebaseVM.addDataToDocument(
            FirebaseConstants.AVISOS_DOC,
            aviso.getHash(),
            id
        )
    }

    override fun onActivityResult(
            requestCode: Int,
            resultCode: Int,
            data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val aviso = data!!.getSerializableExtra("aviso") as Aviso

            atualizarAvisosFB(aviso, avisos.size)
            avisoListAdapter!!.notifyDataSetChanged()
        }
        if (requestCode == 100 && resultCode == RESULT_CANCELED) {
            avisoListAdapter!!.notifyDataSetChanged()
        }
    }

    private fun registerDialogStart(aviso: Aviso?) {

        val builder = AlertDialog.Builder(this)
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT

        if (aviso != null) {
            input.setText(aviso.texto)
            builder.setNeutralButton("Remover"){_, _ ->
                aviso.ativo = false
                atualizarAvisosFB(aviso, aviso.id!!.toInt())
            }
            builder.setPositiveButton("Alterar") {_, _ ->
                aviso.texto = input.text.toString()
                aviso.data = Timestamp.now()
                avisos.sortByDescending { it!!.data }
                atualizarAvisosFB(aviso, aviso.id!!.toInt())
            }
        }else{
            builder.setPositiveButton("Adicionar") {_, _ ->
                val aviso = Aviso(input.text.toString(), usuario, avisos.size, true)
                atualizarAvisosFB(aviso, avisos.size)
            }
        }

        builder.setView(input)
        builder.setTitle("Aviso")
        builder.setMessage("Insira o aviso:")
        builder.setNegativeButton("Cancelar") { dialog, which -> dialog.cancel() }
        builder.show()
    }

    private fun defineAtualizarLista(recyclerView: RecyclerView) {
        val document = FirebaseVM.getDocument(FirebaseConstants.AVISOS_DOC)
        document.addSnapshotListener { value, error ->
            if (error != null) {
                Log.v("App", "ERRO: " + error.printStackTrace())
            }

            if (value != null) {
                val documentTask = FirebaseVM.getDocumentTask(FirebaseConstants.AVISOS_DOC)

                documentTask.addOnCompleteListener {
                    val lista = it.result.data as HashMap<String, Any>
                    for (i in range(avisos.size, lista.size)) {
                        val aviso =
                            lista[FirebaseConstants.AVISOS_FIELD_MENSAGEM + i] as HashMap<String, Any>
                        val campoTexto = aviso[FirebaseConstants.AVISOS_FIELD_CORPO] as String
                        val data = aviso[FirebaseConstants.AVISOS_FIELD_DATA] as Timestamp
                        val usuarioFB =
                            aviso[FirebaseConstants.AVISOS_FIELD_USUARIO] as HashMap<String, Any?>
                        val id = aviso[FirebaseConstants.AVISOS_FIELD_ID] as Number
                        val ativo = aviso[FirebaseConstants.AVISOS_FIELD_ATIVO] as Boolean

                        avisos.add(
                            Aviso(
                                campoTexto,
                                data,
                                usuarioFB,
                                id,
                                ativo
                            )
                        )

                    }
                    avisos.sortByDescending { it!!.data }
                    avisoListAdapter = AvisoListAdapter(
                        avisos
                    ) { aviso ->
                        if (usuario!!.tipoUsuario == TipoUsuario.ESCOLA) {
                            callRegisterActivity(aviso)
                        }
                    }
                    recyclerView.adapter = avisoListAdapter


                }
            }
        }
    }
}

