package br.com.capisorteios_ktx

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import br.com.capisorteios_ktx.model.Rifa
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class CadastroRifaFragment : Fragment() {

    private val rifaCollectionRef = Firebase.firestore.collection("rifas")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cadastro_rifa, container, false)

        val etTitulo = view.findViewById<EditText>(R.id.edtTitulo)
        val etAutor = view.findViewById<EditText>(R.id.edtAutor)
        val etPremio = view.findViewById<EditText>(R.id.edtPremio)
        val etValor = view.findViewById<EditText>(R.id.edtValor)
        val etDataFinal = view.findViewById<EditText>(R.id.edtDataFinal)
        val btnCadastrarRifa = view.findViewById<Button>(R.id.btnCadastrarRifa)

        btnCadastrarRifa.setOnClickListener {
            val titulo = etTitulo.text.toString()
            val autor = etAutor.text.toString()
            val premio = etPremio.text.toString()
            val valor = etValor.text.toString()
            val dataFinal = etDataFinal.text.toString()

            val rifa = Rifa(titulo,autor,premio,valor, dataFinal)
            cadastrarRifa(rifa)
        }

        return view
    }

    private fun cadastrarRifa(rifa: Rifa) = CoroutineScope(Dispatchers.IO).launch {
        try {
            rifaCollectionRef.add(rifa).await()
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Rifa Adicionada com Sucesso", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_cadastroRifaFragment_to_homeFragment)
            }
        } catch (e : Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context,e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

}