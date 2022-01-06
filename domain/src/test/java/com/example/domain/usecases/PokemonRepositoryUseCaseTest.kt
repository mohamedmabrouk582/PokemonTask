package com.example.domain.usecases

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
class PokemonRepositoryUseCaseTest {
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope()



    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineScope.cleanupTestCoroutines()
    }

    @Test
    fun `getRandomPokemonDetail success`() = testCoroutineDispatcher.runBlockingTest {
        TestUtils.testType = TestUtils.TestCases.Success
        val useCase = PokemonRepositoryUseCase(TestUtils.repo)
        val pokemon = useCase.getPokemonDetails().first()
        assertThat(pokemon is com.example.domain.utils.Result.Success).isTrue()
    }

    @Test
    fun `getRandomPokemonDetail fail`() = testCoroutineDispatcher.runBlockingTest {
        TestUtils.testType = TestUtils.TestCases.Fail
        val useCase = PokemonRepositoryUseCase(TestUtils.repo)
        val pokemon = useCase.getPokemonDetails().first()
        assertThat(pokemon is com.example.domain.utils.Result.Fail).isTrue()
    }

    @Test
    fun `getRandomPokemonDetail No connection`() = testCoroutineDispatcher.runBlockingTest {
        TestUtils.testType = TestUtils.TestCases.NoInterNet
        val useCase = PokemonRepositoryUseCase(TestUtils.repo)
        val pokemon = useCase.getPokemonDetails().first()
        assertThat(pokemon is com.example.domain.utils.Result.NoInternetConnection).isTrue()
    }

    @Test
    fun `getRandomPokemonDetail Loading`() = testCoroutineDispatcher.runBlockingTest {
        TestUtils.testType = TestUtils.TestCases.Loading
        val useCase = PokemonRepositoryUseCase(TestUtils.repo)
        val pokemon = useCase.getPokemonDetails().first()
        assertThat(pokemon is com.example.domain.utils.Result.Loading).isTrue()
    }
}