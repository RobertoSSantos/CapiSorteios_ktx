package br.com.capisorteios_ktx.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import br.com.capisorteios_ktx.R
import br.com.capisorteios_ktx.databinding.ActivityMainBinding

/**
 * Equipe:
 * Antônio Horácio
 * Joseph Neiva
 * Matheus Sá
 * Pedro Menezes
 * Roberto Santos
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

}