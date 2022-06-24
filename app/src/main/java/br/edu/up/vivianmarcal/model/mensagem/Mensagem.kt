package br.edu.up.vivianmarcal.model.mensagem

import br.edu.up.vivianmarcal.firebase.FirebaseConstants
import br.edu.up.vivianmarcal.model.usuario.Usuario
import com.google.firebase.Timestamp
import java.io.Serializable
import java.util.ArrayList
import kotlin.collections.HashMap

class Mensagem : Serializable {
    var usuario: Usuario? = null
    var origem: OrigemEnum? = null
    private var nome: String? = null
    var texto: String
    var data: Timestamp? = null

    constructor(origem: OrigemEnum?, texto: String, data: Timestamp?) {
        this.origem = origem
        this.texto = texto
        this.data = data
    }

    constructor(usuario: Usuario?, texto: String) {
        this.usuario = usuario
        this.texto = texto
    }


    fun getHash(): HashMap<String, Any?> {
        return hashMapOf(
            FirebaseConstants.MENSAGENS_FIELD_USUARIO to usuario!!.getHash(),
            FirebaseConstants.MENSAGENS_FIELD_DATA to Timestamp.now(),
            FirebaseConstants.MENSAGENS_FIELD_CORPO to texto
        )
    }

    companion object {
        private const val serialVersionUID = -4740438769141831046L
        val instanceList: ArrayList<Mensagem>
            get() = ArrayList()
    }
}