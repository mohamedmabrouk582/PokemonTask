package com.example.data.repository

import android.content.Context
import com.example.data.api.PokemonApi
import com.example.data.utils.executeCall
import com.example.domain.models.Pokemon
import com.example.domain.repository.PokemonRepository
import com.example.domain.utils.Result
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @name Mohamed Mabrouk
 * Copyrights (c) 16/12/2021 created by Just clean
 */

class PokemonDetailsRepository @Inject constructor(
    @ApplicationContext val context: Context,
    val api: PokemonApi
) : PokemonRepository {
    override suspend fun getPokemonDetails(): Flow<Result<Pokemon>> {
        return executeCall(context){
            api.getPokemonDetailsAsync((1 .. 898).random())
        }
    }
}