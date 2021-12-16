package com.example.domain.repository

import com.example.domain.models.Pokemon
import com.example.domain.utils.Result
import kotlinx.coroutines.flow.Flow

/**
 * @name Mohamed Mabrouk
 * Copyrights (c) 16/12/2021 created by Just clean
 */
interface PokemonRepository {
    suspend fun getPokemonDetails() : Flow<Result<Pokemon>>
}