package com.afp.scarab.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afp.scarab.R
import com.afp.scarab.viewmodel.CountriesViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: CountriesViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CountriesAdapter
    private lateinit var regionSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        regionSpinner = findViewById(R.id.regionSpinner)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CountriesAdapter(emptyList()) { country ->
            Toast.makeText(this, "Seleccionaste: ${country.name.common}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter

        //listado de regiones
        val regions = listOf("Americas", "Asia", "Europe", "Oceania", "Africa")

        //config spiner
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, regions)
        regionSpinner.adapter = spinnerAdapter

        //spiner para seleccion de regiones
        regionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedRegion = regions[position].lowercase()
                viewModel.loadCountries(selectedRegion)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        viewModel.countries.observe(this) { countries ->
            adapter.updateData(countries)
        }

    }
}
