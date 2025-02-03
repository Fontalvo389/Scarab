package com.afp.scarab.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.afp.scarab.api.RetrofitInstance
import com.afp.scarab.model.Country
import kotlinx.coroutines.launch

class CountriesViewModel : ViewModel() {
    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries
    private val _selectedCountries = MutableLiveData<List<Country>>()
    val selectedCountries: LiveData<List<Country>> get() = _selectedCountries
    fun loadCountries(region: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getCountriesByRegion(region)
                _countries.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun selectCountries(selected: List<Country>) {
        _selectedCountries.value = selected
    }
}