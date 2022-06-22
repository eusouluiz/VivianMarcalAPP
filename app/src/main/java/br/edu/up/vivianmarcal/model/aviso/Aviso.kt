package br.edu.up.vivianmarcal.model.aviso

import br.edu.up.vivianmarcal.firebase.FirebaseConstants
import br.edu.up.vivianmarcal.model.mensagem.Mensagem
import br.edu.up.vivianmarcal.model.mensagem.OrigemEnum
import br.edu.up.vivianmarcal.model.usuario.Usuario
import com.google.firebase.Timestamp
import java.io.Serializable
import kotlin.collections.ArrayList

class Aviso : Serializable {
    var texto: String
    var hora: String? = null
    var origem: OrigemEnum? = null
    var usuario: Usuario? = null

    constructor(texto: String, hora: String?, origem: OrigemEnum?) {
        this.texto = texto
        this.hora = hora
        this.origem = origem
    }

    constructor(texto: String, usuario: Usuario?) {
        this.texto = texto
        this.usuario = usuario
    }

    fun getHash(): HashMap<String, Any?> {
        return hashMapOf(
            FirebaseConstants.AVISOS_FIELD_CORPO to texto,
            FirebaseConstants.AVISOS_FIELD_DATA to Timestamp.now(),
            FirebaseConstants.AVISOS_FIELD_USUARIO to usuario!!.getHash()

        )
    }

    companion object {
        private const val serialVersionUID = -5938145431131500078L
        val instanceList: ArrayList<Aviso>
            get() = ArrayList()
    }
}