package com.example.udemicurso

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.example.udemicurso.databinding.ActivityMainBinding
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Inflar o layout usando View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Definir a visualização da atividade para a raiz do layout inflado
        setContentView(binding.root)

        // Configurar o botão de calcular
        binding.calcular.setOnClickListener {
            calcularCusto()
        }
    }

    private fun enableEdgeToEdge() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun calcularCusto() {
        // Obter os valores dos EditTexts e converter para String
        val distanceStr = binding.editDistance.text.toString().replace(",", ".")
        val precoStr = binding.preco.text.toString().replace(",", ".")
        val autonomiaStr = binding.autonomia.text.toString().replace(",", ".")

        // Verificar se os campos não estão vazios
        if (distanceStr.isNotEmpty() && precoStr.isNotEmpty() && autonomiaStr.isNotEmpty()) {
            // Converter os valores para Float
            val distance = distanceStr.toFloatOrNull()
            val preco = precoStr.toFloatOrNull()
            val autonomia = autonomiaStr.toFloatOrNull()

            if (distance != null && preco != null && autonomia != null) {
                // Calcular o custo total
                val custoTotal = (distance * preco) / autonomia

                // Exibir o resultado no TextView
                binding.custo.text = "R$ %.2f".format(custoTotal)
            } else {
                // Se a conversão falhar, exibir uma mensagem de erro
                binding.custo.text = "Valores inválidos"
            }
        } else {
            // Se algum campo estiver vazio, exibir uma mensagem de erro
            binding.custo.text = getString(R.string.preencha_todos_os_campos)


        }
        // Limpar os EditTexts após o cálculo
        binding.editDistance.text.clear()
        binding.preco.text.clear()
        binding.autonomia.text.clear()
    }
}




