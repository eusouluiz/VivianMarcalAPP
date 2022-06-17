package br.edu.up.vivianmarcal.model.aviso

import br.edu.up.vivianmarcal.firebase.FirebaseConstants
import com.google.firebase.Timestamp
import java.io.Serializable

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
            FirebaseConstants.AVISOS_FIELD_DATA to Timestamp.now(),
            FirebaseConstants.AVISOS_FIELD_CORPO to texto
        )
    }

    companion object {
        private const val serialVersionUID = -5938145431131500078L
    }
}