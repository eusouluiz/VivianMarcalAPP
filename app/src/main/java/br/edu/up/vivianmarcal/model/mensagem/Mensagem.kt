package br.edu.up.vivianmarcal.model.mensagem

import com.google.firebase.Timestamp
import java.io.Serializable
import java.util.ArrayList
import kotlin.collections.HashMap

class Mensagem : Serializable {
    var origem: OrigemEnum? = null
    private var nome: String? = null
    var texto: String
    var hora: String? = null

    constructor(origem: OrigemEnum?, nome: String?, texto: String) {
        this.origem = origem
        this.texto = texto
        this.nome = nome
    }

    constructor(origem: OrigemEnum?, nome: String?, texto: String, hora: String?) {
        this.origem = origem
        this.nome = nome
        this.texto = texto
        this.hora = hora
    }

    fun getHash(): HashMap<String, Any?> {
        return hashMapOf(
            "nome" to nome,
            "id" to origem!!.id as Number,
            "data" to Timestamp.now(),
            "corpo" to texto
        )
    }

    companion object {
        private const val serialVersionUID = -4740438769141831046L
        val instanceList: ArrayList<Mensagem>
            get() = ArrayList()
    }
}