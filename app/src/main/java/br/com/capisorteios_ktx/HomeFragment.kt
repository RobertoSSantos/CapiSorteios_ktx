package br.com.capisorteios_ktx

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.StringBuilder

class HomeFragment : Fragment() {

    private val rifaCollectionRef = Firebase.firestore.collection("rifas")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        retrieve()

        val btnPerfil = view.findViewById<Button>(R.id.btnPerfil)
        btnPerfil.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        return view
    }

    private fun retrieve() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val querySnapshot = rifaCollectionRef.get().await()
            val rifaList = ArrayList<Rifa>()
            val arrayAdapter: ArrayAdapter<Rifa>
            for(document in querySnapshot) {
                val rifa = document.toObject<Rifa>()
                rifaList.add(rifa)
            }
            withContext(Dispatchers.Main){
                var lvRifas = view?.findViewById<ListView>(R.id.list_rifa)
                arrayAdapter = view?.let { ArrayAdapter(it.context, android.R.layout.simple_list_item_1, rifaList) }!!
                if (lvRifas != null) {
                    lvRifas.adapter=arrayAdapter
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

}