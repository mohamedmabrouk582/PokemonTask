package com.example.pokemontask.viewmodels

import com.example.pokemontask.TestUtils
import com.example.pokemontask.intent.PokemonStates
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * @name Mohamed Mabrouk
 * Copyrights (c) 16/12/2021 created by Just clean
 */
@ExperimentalCoroutinesApi
class PokemonViewModelTest {
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope()

    lateinit var viewModel: PokemonViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineDispatcher)
        viewModel = PokemonViewModel(FakeRepository())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineScope.cleanupTestCoroutines()
    }

    @Test
    fun `getRandomPokemonDetail success`() = testCoroutineDispatcher.runBlockingTest {
        TestUtils.testType = TestUtils.TestCases.Success
        viewModel.getRandomPokemon()
        assertThat(viewModel.pokemonStates.first() is PokemonStates.LoadPokemonDetails).isTrue()
    }

    @Test
    fun `getRandomPokemonDetail fail`() = testCoroutineDispatcher.runBlockingTest {
        TestUtils.testType = TestUtils.TestCases.Fail
        viewModel.getRandomPokemon()
        assertThat(viewModel.pokemonStates.first() is PokemonStates.Error).isTrue()
    }

    @Test
    fun `getRandomPokemonDetail No connection`() = testCoroutineDispatcher.runBlockingTest {
        TestUtils.testType = TestUtils.TestCases.NoInterNet
        viewModel.getRandomPokemon()
        assertThat(viewModel.pokemonStates.first() is PokemonStates.Error).isTrue()
    }
}