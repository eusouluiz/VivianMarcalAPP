package br.edu.up.vivianmarcal

import androidx.appcompat.app.AppCompatActivity
import br.edu.up.vivianmarcal.adapter.MensagemListAdapter
import br.edu.up.vivianmarcal.mensagem.model.Mensagem
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.projeto_ds2.R
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.up.vivianmarcal.firebase.FirebaseVM
import com.google.android.material.textfield.TextInputEditText
import br.edu.up.vivianmarcal.mensagem.model.OrigemEnum
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.stream.IntStream.range
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@Suppress("CAST_NEVER_SUCCEEDS")
class MensagensActivity : AppCompatActivity() {
    var mensagemListAdapter: MensagemListAdapter? = null
    private val mensagens = ArrayList<Mensagem>()
    var sdf = SimpleDateFormat("HH:mm")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                OrigemEnum.Remetente, "nomeTeste", campoMensagem.text.toString()
            )
            FirebaseVM.addDataToDocument("Mensagens", novaMensagem.getHash(), mensagens.size)

            campoMensagem.setText("")

        }
    }

    private fun defineAtualizarLista(recyclerView: RecyclerView, entrada: Boolean = true) {
        val document = FirebaseVM.getDocument("Mensagens")
        document.addSnapshotListener { value, error ->
            if (error != null) {
                Log.v("App", "ERRO: " + error.printStackTrace())
            }

            if (value != null) {
                val documentTask = FirebaseVM.getDocumentTask("Mensagens")

                documentTask.addOnCompleteListener {
                    val lista = it.result.data as HashMap<String, Any>
                    for (i in range(mensagens.size, lista.size)) {
                        val mensagem = lista["mensagem$i"] as HashMap<String, Any>
                        val campoTexto = mensagem["corpo"] as String
                        val nome = mensagem["nome"] as String
                        val id = mensagem["id"] as Number
                        val data = mensagem["data"] as Timestamp

                        if (id.toInt() == 0) {
                            mensagens.add(
                                Mensagem(
                                    OrigemEnum.Remetente,
                                    nome,
                                    campoTexto,
                                    sdf.format(data.toDate().time)
                                )
                            )
                        } else {
                            mensagens.add(
                                Mensagem(
                                    OrigemEnum.Destinatario,
                                    nome,
                                    campoTexto,
                                    sdf.format(data.toDate().time)
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