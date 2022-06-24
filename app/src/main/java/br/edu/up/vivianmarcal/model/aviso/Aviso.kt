package br.edu.up.vivianmarcal.model.aviso

import android.text.Editable
import br.edu.up.vivianmarcal.firebase.FirebaseConstants
import br.edu.up.vivianmarcal.model.mensagem.Mensagem
import br.edu.up.vivianmarcal.model.mensagem.OrigemEnum
import br.edu.up.vivianmarcal.model.usuario.Usuario
import com.google.firebase.Timestamp
import java.io.Serializable
import kotlin.collections.ArrayList

class Aviso : Serializable {
    var texto: String? = null
    var hora: String? = null
    var usuario: Usuario? = null
    var id: Number? = null
    var ativo: Boolean? = null


    constructor(texto: String?, usuario: Usuario?, id: Number?, ativo: Boolean?) {
        this.texto = texto
        this.usuario = usuario
        this.id = id
        this.ativo = ativo
    }

    constructor(texto: String?, hora: String?, usuarioHash: HashMap<String, Any?>, id: Number?, ativo: Boolean?) {
        this.texto = texto
        this.hora = hora
        this.usuario = Usuario.hashAsUsuario(usuarioHash)
        this.id = id
        this.ativo = ativo
    }


    fun getHash(): HashMap<String, Any?> {
        return hashMapOf(
            FirebaseConstants.AVISOS_FIELD_CORPO to texto,
            FirebaseConstants.AVISOS_FIELD_DATA to Timestamp.now(),
            FirebaseConstants.AVISOS_FIELD_USUARIO to usuario!!.getHash(),
            FirebaseConstants.AVISOS_FIELD_ID to id,
            FirebaseConstants.AVISOS_FIELD_ATIVO to ativo

        )
    }

    companion object {
        private const val serialVersionUID = -5938145431131500078L
        val instanceList: ArrayList<Aviso>
            get() = ArrayList()
    }
}