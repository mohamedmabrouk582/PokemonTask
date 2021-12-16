package com.example.domain.models

/**
 * @name Mohamed Mabrouk
 * Copyrights (c) 15/12/2021 created by Just clean
 */
data class Pokemon(
    val id : Int ,
    val name :String ,
    val weight :Int = 0 ,
    val height : Int = 0,
    val stats : ArrayList<PokemonStatistics> = arrayListOf() ,
    val abilities : ArrayList<PokemonAbilities> = arrayListOf(),
    val moves : ArrayList<PokemonMove> = arrayListOf(),
    val types : ArrayList<PokemonType> = arrayListOf()
){
    fun image() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}