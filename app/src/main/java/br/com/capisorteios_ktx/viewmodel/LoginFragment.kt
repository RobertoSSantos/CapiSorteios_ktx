package br.com.capisorteios_ktx.viewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import br.com.capisorteios_ktx.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {


    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        auth = FirebaseAuth.getInstance()

        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val btnRegistro = view.findViewById<Button>(R.id.btnRegistro)

        val edtLoginEmail = view.findViewById<EditText>(R.id.edtLoginEmail)
        val edtLoginSenha = view.findViewById<EditText>(R.id.edtLoginSenha)

        btnLogin.setOnClickListener {
            val email = edtLoginEmail.text.toString()
            val senha = edtLoginSenha.text.toString()

            loginUsuario(email, senha)
        }

        btnRegistro.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_cadastroFragment)
        }

        return view
    }

    private fun loginUsuario(email : String, senha : String) = CoroutineScope(Dispatchers.IO).launch {
        try {
            auth.signInWithEmailAndPassword(email, senha).await()
            withContext(Dispatchers.Main){
                Toast.makeText(context, "Bem Vindo", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        } catch (e : Exception){
            Toast.makeText(context,e.message, Toast.LENGTH_LONG).show()
        }
    }

}