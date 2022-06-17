package br.edu.up.vivianmarcal.model.aviso

import br.edu.up.vivianmarcal.firebase.FirebaseConstants
import br.edu.up.vivianmarcal.model.mensagem.Mensagem
import com.google.firebase.Timestamp
import java.io.Serializable
import kotlin.collections.ArrayList

class Aviso : Serializable {
    var texto: String
    var hora: String? = null

    constructor(texto: String, hora: String?) {
        this.texto = texto
        this.hora = hora
    }

    constructor(texto: String) {
        this.texto = texto
    }

    fun getHash(): HashMap<String, Any?> {
        return hashMapOf(
            FirebaseConstants.AVISOS_FIELD_CORPO to toString(),
            FirebaseConstants.AVISOS_FIELD_DATA to Timestamp.now()

        )
    }

    companion object {
        private const val serialVersionUID = -5938145431131500078L
        val instanceList: ArrayList<Aviso>
            get() = ArrayList()
    }
}