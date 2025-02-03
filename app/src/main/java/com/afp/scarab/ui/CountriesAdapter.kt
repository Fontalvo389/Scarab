package com.afp.scarab.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.afp.scarab.model.Country
import com.afp.scarab.R

class CountriesAdapter (
    private var countries: List<Country>,
    private val onCountrySelected: (Country) -> Unit
) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val countryName: TextView = view.findViewById(R.id.countryName)
            val countryFlag: ImageView = view.findViewById(R.id.countryFlag)
            //val checkBox: CheckBox = view.findViewById(R.id.checkBox)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_country, parent, false)
            return ViewHolder(view)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.countryName.text = country.name.common
        Glide.with(holder.itemView.context).load(country.flags.png).into(holder.countryFlag)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, CountryDetailsActivity::class.java).apply {
                putExtra("nameOfficial", country.name.official)
                putExtra("capital", country.capital?.joinToString(", ") ?: "No disponible")
                putExtra("population", country.population)
                putExtra("languages", country.languages?.values?.joinToString(", ") ?: "No disponible")
                putExtra("currencies", country.currencies?.values?.joinToString(", ") { "${it.name} (${it.symbol})" } ?: "No disponible")
                putExtra("flagUrl", country.flags.png)
            }
            holder.itemView.context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int = countries.size

        fun updateData(newCountries: List<Country>) {
            countries = newCountries
            notifyDataSetChanged()
        }
    }

