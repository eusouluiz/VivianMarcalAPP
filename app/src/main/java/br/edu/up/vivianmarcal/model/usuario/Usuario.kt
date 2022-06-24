package br.edu.up.vivianmarcal.model.usuario

import com.google.firebase.Timestamp
import java.io.Serializable

class Usuario : Serializable {
    var id: Number = 0
    var nome: String
    var tipoUsuario: String? = null

    constructor(id: Number, nome: String, tipoUsuario: String?) {
        this.id = id
        this.nome = nome
        this.tipoUsuario = tipoUsuario
    }

    constructor(nome: String) {
        this.nome = nome
    }

    fun getHash(): HashMap<String, Any?> {
        return hashMapOf(
            "id" to id,
            "nome" to nome,
            "tipoUsuario" to tipoUsuario
        )
    }

    companion object {
        private const val serialVersionUID = 5500378251488245594L

        fun hashAsUsuario(hash: HashMap<String, Any?>): Usuario{
            val id = hash["id"] as Number
            val nome = hash["nome"] as String
            val tipoUsuario = hash["tipoUsuario"] as String
            return Usuario(id, nome, tipoUsuario)
        }
    }
}