package br.edu.up.vivianmarcal

import androidx.appcompat.app.AppCompatActivity
import br.edu.up.vivianmarcal.adapter.MensagemListAdapter
import br.edu.up.vivianmarcal.model.mensagem.Mensagem
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.projeto_ds2.R
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.up.vivianmarcal.firebase.FirebaseConstants
import br.edu.up.vivianmarcal.firebase.FirebaseVM
import com.google.android.material.textfield.TextInputEditText
import br.edu.up.vivianmarcal.model.mensagem.OrigemEnum
import br.edu.up.vivianmarcal.model.usuario.Usuario
import com.google.firebase.Timestamp
import java.util.stream.IntStream.range
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@Suppress("CAST_NEVER_SUCCEEDS")
class MensagensActivity : AppCompatActivity() {
    var mensagemListAdapter: MensagemListAdapter? = null
    private var usuario: Usuario? = null
    private val mensagens = ArrayList<Mensagem>()

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

        setContentView(R.layout.activity_mensagens)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_mensagens)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.stackFromEnd = true
        recyclerView.layoutManager = linearLayoutManager

        defineAtualizarLista(recyclerView)

        val campoMensagem = findViewById<TextInputEditText>(R.id.edit_text_mensagem)
        campoMensagem.requestFocus()
        val botaoEnvio = findViewById<Button>(R.id.button_mensagem)
        botaoEnvio.setOnClickListener {
            var novaMensagem = Mensagem(
                usuario, campoMensagem.text.toString()
            )
            FirebaseVM.addDataToDocument(
                FirebaseConstants.MENSAGENS_DOC,
                novaMensagem.getHash(),
                mensagens.size
            )

            campoMensagem.setText("")

        }
    }

    private fun defineAtualizarLista(recyclerView: RecyclerView) {
        val document = FirebaseVM.getDocument(FirebaseConstants.MENSAGENS_DOC)
        document.addSnapshotListener { value, error ->
            if (error != null) {
                Log.v("App", "ERRO: " + error.printStackTrace())
            }

            if (value != null) {
                val documentTask = FirebaseVM.getDocumentTask(FirebaseConstants.MENSAGENS_DOC)

                documentTask.addOnCompleteListener {
                    val lista = it.result.data as HashMap<String, Any>
                    for (i in range(mensagens.size, lista.size)) {
                        val mensagem =
                            lista[FirebaseConstants.MENSAGENS_FIELD_MENSAGEM + i] as HashMap<String, Any>
                        val campoTexto = mensagem[FirebaseConstants.MENSAGENS_FIELD_CORPO] as String
                        val usuarioFB =
                            mensagem[FirebaseConstants.MENSAGENS_FIELD_USUARIO] as HashMap<String, Any>
                        val data = mensagem[FirebaseConstants.MENSAGENS_FIELD_DATA] as Timestamp

                        if (usuarioFB["id"] == usuario!!.id) {
                            mensagens.add(
                                Mensagem(
                                    OrigemEnum.Remetente,
                                    campoTexto,
                                    data
                                )
                            )
                        } else {
                            mensagens.add(
                                Mensagem(
                                    OrigemEnum.Destinatario,
                                    campoTexto,
                                    data
                                )
                            )
                        }
                    }
                    mensagemListAdapter = MensagemListAdapter(mensagens)
                    recyclerView.adapter = mensagemListAdapter

                }


            }
        }
    }
}