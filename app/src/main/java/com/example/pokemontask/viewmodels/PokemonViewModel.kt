package com.example.pokemontask.viewmodels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.PokemonRepository
import com.example.domain.utils.Result
import com.example.pokemontask.intent.PokemonStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @name Mohamed Mabrouk
 * Copyrights (c) 16/12/2021 created by Just clean
 */
@HiltViewModel
class PokemonViewModel @Inject constructor(val repository: PokemonRepository) : ViewModel() , LifecycleObserver {
    private val _pokemonStates = MutableStateFlow<PokemonStates>(PokemonStates.Loading)
    val pokemonStates : StateFlow<PokemonStates> = _pokemonStates

    fun getRandomPokemon(){
        viewModelScope.launch {
            repository.getPokemonDetails().collect {
                when(it){
                    Result.Loading -> _pokemonStates.value = PokemonStates.Loading
                    is Result.Fail -> _pokemonStates.value = PokemonStates.Error(it.error.message?:"")
                    is Result.NoInternetConnection -> _pokemonStates.value = PokemonStates.Error(it.error)
                    is Result.Success -> _pokemonStates.value = PokemonStates.LoadPokemonDetails(it.data)
                }
            }
        }
    }

}