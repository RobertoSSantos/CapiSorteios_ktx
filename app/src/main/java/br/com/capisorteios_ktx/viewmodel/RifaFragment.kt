package br.com.capisorteios_ktx.viewmodel

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import br.com.capisorteios_ktx.R

class RifaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rifa, container, false)


        // Dentro da sua Activity ou Fragment
        //val gridLayout: GridLayout = view.findViewById<GridLayout>(R.id.gridRifa)
        val gridLayout = view.findViewById<GridLayout>(R.id.gridRifa)
        val totalRows = 10 // Número total de linhas
        val totalColumns = 4 // Número total de colunas

        for (row in 0 until totalRows) {
            for (col in 0 until totalColumns) {
                val numero = (row * totalColumns) + col + 1
                val button = Button(context) // Ou context, se estiver dentro de um Fragment
                button.text = numero.toString()

                button.isSelected = false;
                button.setOnClickListener {
                    if(button.isSelected){
                        button.setBackgroundColor(Color.GRAY);
                        button.isSelected = false;
                    }
                    else{
                        button.setBackgroundColor(Color.RED);
                        button.isSelected = true;
                    }
                }

                val params = GridLayout.LayoutParams().apply {
                    width = GridLayout.LayoutParams.WRAP_CONTENT
                    height = GridLayout.LayoutParams.WRAP_CONTENT
                    setMargins(4, 4, 4, 4)
                    rowSpec = GridLayout.spec(row)
                    columnSpec = GridLayout.spec(col)
                }
                gridLayout.addView(button, params)
            }
        }

        return view
    }

}