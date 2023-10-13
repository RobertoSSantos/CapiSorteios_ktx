package br.com.capisorteios_ktx

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import br.com.capisorteios_ktx.model.Consumidor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class CadastroFragment : Fragment() {

    private val usuariosCollectionRef = Firebase.firestore.collection("Usuarios")
    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cadastro, container, false)
        auth = FirebaseAuth.getInstance()

        val edtNome = view.findViewById<EditText>(R.id.edtNome)
        val edtCpf = view.findViewById<EditText>(R.id.edtCpf)
        val edtEmail = view.findViewById<EditText>(R.id.edtEmail)
        val edtSenha = view.findViewById<EditText>(R.id.edtSenha)
        val checkAdm = view.findViewById<CheckBox>(R.id.checkAdm)
        val edtPix = view.findViewById<EditText>(R.id.edtChavePix)
        val btnCadastroUsuario = view.findViewById<Button>(R.id.btnCadastrarUsuario)

        btnCadastroUsuario.setOnClickListener {
            val nome = edtNome.text.toString()
            val cpf = edtCpf.text.toString()
            val email = edtEmail.text.toString()
            val senha = edtSenha.text.toString()
            val chavePix = edtPix.text.toString()

            val usuario = if (checkAdm.isChecked) {
                Consumidor(nome, cpf, email, senha, chavePix,true)
            } else {
                Consumidor(nome, cpf, email, senha, chavePix)
            }

            cadastrarUsuario(usuario)
        }



        return view
    }

    private fun cadastrarUsuario(usuario : Consumidor) = CoroutineScope(Dispatchers.IO).launch {
        try {
            usuariosCollectionRef.add(usuario).await()
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Usuario Adicionado com Sucesso", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_cadastroFragment_to_loginFragment)
            }
        } catch (e : Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context,e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

}