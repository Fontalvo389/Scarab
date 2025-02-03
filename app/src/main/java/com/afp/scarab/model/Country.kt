package com.afp.scarab.model

data class Country(
    val name: Name,
    val flags: Flags,
    val population: Int,
    val capital: List<String>?,
    val languages: Map<String, String>?,
    val currencies: Map<String, Currency>?
)
data class Name(val common: String, val official: String)
data class Flags(val png: String)
data class Currency(val name: String, val symbol: String)
