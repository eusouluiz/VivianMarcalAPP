package br.edu.up.vivianmarcal.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseVM {
    companion object {

        private const val COLLECTION_VIVIANMARCAL = "VivianMarcal"

        private fun getCollection(): CollectionReference{
            val firestore = Firebase.firestore
            return firestore.collection(COLLECTION_VIVIANMARCAL)
        }

        fun getDocumentTask(document: String): Task<DocumentSnapshot> {
            val docTask = getCollection().document(document)
            return docTask.get()
        }

        fun addDataToDocument(document: String, data: Any, size: Int) {
            if (document.toString().equals("Mensagens")) {
                getCollection().document(document).update("mensagem$size", data)
            }
            if (document.toString().equals("Avisos")) {
                getCollection().document(document).update("aviso$size", data)
            }
        }

        fun getDocument(document: String): DocumentReference {
            return getCollection().document(document)
        }
    }


}