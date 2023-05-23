package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.R
import com.example.motivation.data.Mock
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Esconder barra de navegação
        supportActionBar?.hide()

        // quando o app abre, ja chama essas implementações

        handleFilter(R.id.image_all) // inicia a activity com o image_all selecionado
        handleNextPhrase()

        //Eventos
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.textUserName.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        handleUserName()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase) {
            handleNextPhrase()
        } else if (view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)) { // cria uma lista com os ID das imagens para nao precisar utilizar tantos if else
            handleFilter(view.id) // criar um filtro para tratar a lógica dentro do OnClick
        } else if (view.id == R.id.text_user_name) {
            startActivity(Intent(this, UserActivity::class.java))
        }
    }

    private fun handleNextPhrase() {
        val phrase = Mock().getPhrase(categoryId)
        binding.textPhrase.text = phrase
    }

    private fun handleFilter(id: Int) {

        // trocar a cor das outras opções selecionadas para dark_purple ao cliclar em alguma opção
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        // trocar a cor da opção selecionada para branco
        if (id == R.id.image_all) {
            binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
            categoryId = MotivationConstants.FILTER.ALL
        } else if (id == R.id.image_happy) {
            binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
            categoryId = MotivationConstants.FILTER.HAPPY
        } else if (id == R.id.image_sunny) {
            binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
            categoryId = MotivationConstants.FILTER.SUNNY
        }
    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME) // busca o nome do usuário
        binding.textUserName.text = "Olá, $name!"
    }
}