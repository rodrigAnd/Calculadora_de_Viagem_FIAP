package com.example.calculadoradeviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.calculadoradeviagem.databinding.ActivityMainBinding
import com.example.imc.extensions.format
import com.example.imc.extensions.valueDouble
import com.example.calculadoradeviagem.utils.DecimalTextWatcher
import com.example.calculadoradeviagem.utils.hideKeyboard

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        hideComponents()
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnCalcular.setOnClickListener {
            binding.btnCalcular.hideKeyboard()
            calcular()
        }

        binding.edValorDoCombustivel.addTextChangedListener(DecimalTextWatcher(binding.edValorDoCombustivel, 2))
        binding.edDistanciaASerPercorrida.addTextChangedListener(DecimalTextWatcher(binding.edDistanciaASerPercorrida, 2))
        binding.edAutonomia.addTextChangedListener(DecimalTextWatcher(binding.edAutonomia, 2))
    }

    private fun calcular() {
        val valCombustivel = binding.edValorDoCombustivel.valueDouble()
        val valDistanciaPercorrida = binding.edDistanciaASerPercorrida.valueDouble()
        val valAutonomia = binding.edAutonomia.valueDouble()

        if (valCombustivel == 0.0 || valDistanciaPercorrida == 0.0 ||valAutonomia == 0.0 ) {
            Toast.makeText(this, "Digite os valores corretamente", Toast.LENGTH_SHORT).show()
            return
        }

        calculaValor(valCombustivel, valAutonomia, valDistanciaPercorrida)
    }

    private fun calculaValor(
        valCombustivel: Double,
        valAutonomia: Double,
        valDistanciaPercorrida: Double
    ) {
        val precoPorKm = valCombustivel / valAutonomia
        val resultado = precoPorKm * valDistanciaPercorrida

        binding.tvValor.text = resultado.format(2)
        showComponets()
    }

    private fun showComponets() {
        binding.tvValor.visibility = View.VISIBLE
        binding.tvMoeda.visibility = View.VISIBLE
        binding.tvValorGastoNaViagem.visibility = View.VISIBLE
    }

    private fun hideComponents() {
        binding.tvValor.visibility = View.GONE
        binding.tvMoeda.visibility = View.GONE
        binding.tvValorGastoNaViagem.visibility = View.GONE
    }

}