package com.example.pokemontask.viewmodels

import com.example.domain.models.Pokemon
import com.example.domain.repository.PokemonRepository
import com.example.domain.utils.Result
import com.example.pokemontask.TestUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @name Mohamed Mabrouk
 * Copyrights (c) 16/12/2021 created by Just clean
 */
class FakeRepository : PokemonRepository {

    override suspend fun getPokemonDetails(): Flow<Result<Pokemon>> {
        return flow {
            emit(
                when (TestUtils.testType) {
                    TestUtils.TestCases.Success -> Result.Success(TestUtils.pokemonDetails)
                    TestUtils.TestCases.NoInterNet -> Result.NoInternetConnection("No Internet")
                    TestUtils.TestCases.Fail -> Result.Fail(Throwable("error"))
                }
            )
        }
    }
}