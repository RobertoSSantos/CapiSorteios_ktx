package br.com.capisorteios_ktx

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val btnCriaRifa = view.findViewById<Button>(R.id.btnCriaRifa)
        btnCriaRifa.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_cadastroRifaFragment)
        }


        return view
    }

}