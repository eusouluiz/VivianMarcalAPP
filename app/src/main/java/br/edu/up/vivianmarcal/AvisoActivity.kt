package br.edu.up.vivianmarcal

import android.annotation.SuppressLint
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
import android.widget.Button
import android.widget.EditText
import br.edu.up.vivianmarcal.firebase.FirebaseConstants
import br.edu.up.vivianmarcal.firebase.FirebaseVM
import br.edu.up.vivianmarcal.model.mensagem.OrigemEnum
import br.edu.up.vivianmarcal.model.usuario.TipoUsuario
import br.edu.up.vivianmarcal.model.usuario.Usuario
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.HashMap
import java.util.stream.IntStream.range

@Suppress("CAST_NEVER_SUCCEEDS")
class AvisoActivity : AppCompatActivity() {
    private val avisos = ArrayList<Aviso?>()
    private var usuario: Usuario? = null
    var avisoListAdapter: AvisoListAdapter? = null
    var sdf = SimpleDateFormat("dd/MM HH:mm")

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


        if (usuario!!.tipoUsuario == TipoUsuario.ESCOLA) {
            setContentView(R.layout.activity_aviso)
            val buttonAdicionar = findViewById<Button>(R.id.button_add)
            buttonAdicionar.setOnClickListener { callRegisterActivity(null) }
            val buttonRemover = findViewById<Button>(R.id.button_remove)
            buttonRemover.setOnClickListener { callRemoverActivity(null) }
        } else {
            setContentView(R.layout.activity_aviso_pai)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_avisos)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        defineAtualizarLista(recyclerView)
    }

    fun callRegisterActivity(aviso: Aviso?) {

        var cont: Long = (100..999).random().toLong()
        if (aviso != null) {
            registerDialogStart(aviso, cont)
        } else {
            registerDialogStart(null, cont)
        }
    }

    fun callRemoverActivity(aviso: Aviso?) {

        if (aviso != null) {
            RemoveDialogStart(aviso)
        } else {
            RemoveDialogStart(null)
        }
    }

    override fun onActivityResult(
            requestCode: Int,
            resultCode: Int,
            data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val aviso = data!!.getSerializableExtra("aviso") as Aviso

            FirebaseVM.addDataToDocument(
                    FirebaseConstants.AVISOS_DOC,
                    aviso.getHash(),
                    avisos.size
            )
            avisoListAdapter!!.notifyDataSetChanged()
        }
        if (requestCode == 100 && resultCode == RESULT_CANCELED) {
            avisoListAdapter!!.notifyDataSetChanged()
        }
    }

    private fun registerDialogStart(aviso: Aviso?, cont: Long) {

        val builder = AlertDialog.Builder(this)
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT

        if (aviso != null) {
            input.setText(aviso.texto)
        }

        builder.setView(input)
        builder.setTitle("Aviso")
        builder.setMessage("Insira o aviso:")
        builder.setPositiveButton("Adicionar") { dialog, which ->
            var aviso = Aviso(input.text.toString(), usuario, cont)
            FirebaseVM.addDataToDocument(
                    FirebaseConstants.AVISOS_DOC,
                    aviso.getHash(),
                    avisos.size
            )
        }
        builder.setNegativeButton("Cancelar") { dialog, which -> dialog.cancel() }
        builder.show()
    }

    @SuppressLint("SetTextI18n")
    private fun RemoveDialogStart(aviso: Aviso?) {

        val builder = AlertDialog.Builder(this)
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT

        if (aviso != null) {
            input.setText("" + aviso.texto)
        }

        builder.setView(input)
        builder.setTitle("Remover")
        builder.setMessage("Insira a identificação do aviso:")
        builder.setPositiveButton("Remover") { dialog, which ->
            //funcao para removoção de acordo com o id que digitar
        }
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
                                lista[FirebaseConstants.AVISOS_FIELD_MENSAGEM + i] as HashMap<String, Any>?
                        val campoTexto = aviso?.get(FirebaseConstants.AVISOS_FIELD_CORPO) as String?
                        val data = aviso?.get(FirebaseConstants.AVISOS_FIELD_DATA) as Timestamp?
                        val usuarioFB =
                                aviso?.get(FirebaseConstants.AVISOS_FIELD_USUARIO) as HashMap<String, Any>
                        val id = aviso?.get(FirebaseConstants.AVISOS_FIELD_IDENTIFICACAO) as Long

                        if (usuarioFB["id"] == usuario!!.id) {
                            if (data != null) {
                                avisos.add(
                                        campoTexto?.let { it1 ->
                                            Aviso(
                                                    it1,
                                                    sdf.format(data.toDate().time),
                                                    OrigemEnum.Remetente,
                                                    id

                                            )
                                        }
                                )

                            }

                        } else {
                            if (data != null) {
                                avisos.add(
                                        campoTexto?.let { it1 ->
                                            Aviso(
                                                    it1,
                                                    sdf.format(data.toDate().time),
                                                    OrigemEnum.Destinatario,
                                                    id

                                            )
                                        }
                                )

                            }
                        }
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
}

