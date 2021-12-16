package com.example.domain.usecases

import com.example.domain.models.Pokemon
import com.example.domain.repository.PokemonRepository
import com.example.domain.utils.Result
import kotlinx.coroutines.flow.Flow

/**
 * @name Mohamed Mabrouk
 * Copyrights (c) 16/12/2021 created by Just clean
 */
class PokemonRepositoryUseCase(private val repository: PokemonRepository) {
    suspend fun getPokemonDetails() : Flow<Result<Pokemon>>{
        return repository.getPokemonDetails()
    }
}