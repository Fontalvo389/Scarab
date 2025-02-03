package com.afp.scarab.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.afp.scarab.R
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class CountryDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_details)

        val countryFlag: ImageView = findViewById(R.id.countryFlagLarge)
        val countryName: TextView = findViewById(R.id.countryName)
        val countryCapital: TextView = findViewById(R.id.countryCapital)
        val countryPopulation: TextView = findViewById(R.id.countryPopulation)
        val countryLanguages: TextView = findViewById(R.id.countryLanguages)
        val countryCurrencies: TextView = findViewById(R.id.countryCurrencies)

        // Obtener los datos enviados desde MainActivity
        val nameOfficial = intent.getStringExtra("nameOfficial") ?: "No disponible"
        val capital = intent.getStringExtra("capital") ?: "No disponible"
        val population = intent.getIntExtra("population", 0)
        val languages = intent.getStringExtra("languages") ?: "No disponible"
        val currencies = intent.getStringExtra("currencies") ?: "No disponible"
        val flagUrl = intent.getStringExtra("flagUrl") ?: ""

        //parametros
        countryName.text = "nombre Oficial: $nameOfficial"
        countryCapital.text = "capital: $capital"
        countryPopulation.text = "población: $population"
        countryLanguages.text = "idiomas: $languages"
        countryCurrencies.text = "monedas: $currencies"
        //bandera
        Glide.with(this).load(flagUrl).into(countryFlag)

    }
}