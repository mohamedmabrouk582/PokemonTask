package com.example.domain.usecases

import com.example.domain.models.Pokemon
import com.example.domain.repository.PokemonRepository
import com.example.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @name Mohamed Mabrouk
 * Copyrights (c) 16/12/2021 created by Just clean
 */
object TestUtils {
    var testType: TestCases = TestCases.Success
    val pokemonDetails = Pokemon(1, "name #1")

    enum class TestCases {
        Success, NoInterNet, Fail , Loading
    }

    val repo = object : PokemonRepository {
        override suspend fun getPokemonDetails(): Flow<Result<Pokemon>> {
            return flow {
                emit(
                    when (testType) {
                        TestCases.Success -> Result.Success(pokemonDetails)
                        TestCases.NoInterNet -> Result.NoInternetConnection("No Internet")
                        TestCases.Fail -> Result.Fail(Throwable("error"))
                        TestCases.Loading -> Result.Loading
                    }
                )
            }
        }
    }


}