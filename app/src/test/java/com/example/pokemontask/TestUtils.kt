package com.example.pokemontask

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
        Success, NoInterNet, Fail
    }
}