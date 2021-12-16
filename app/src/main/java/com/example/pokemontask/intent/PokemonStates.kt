package com.example.pokemontask.intent

import com.example.domain.models.Pokemon

/**
 * @name Mohamed Mabrouk
 * Copyrights (c) 16/12/2021 created by Just clean
 */
sealed class PokemonStates(){
    object Loading : PokemonStates()
    data class LoadPokemonDetails(val pokemon : Pokemon) : PokemonStates()
    data class Error(val error : String) : PokemonStates()
}
