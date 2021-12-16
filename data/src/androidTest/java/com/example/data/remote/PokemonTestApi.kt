package com.example.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.MediumTest
import com.example.data.api.PokemonApi
import com.example.domain.models.Pokemon
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import javax.inject.Inject

/**
 * @name Mohamed Mabrouk
 * Copyrights (c) 16/12/2021 created by Just clean
 */
@HiltAndroidTest
@MediumTest
@ExperimentalCoroutinesApi
class PokemonTestApi {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mockWebServer: MockWebServer

    @Inject
    lateinit var pokemonApi: PokemonApi

    @Before
    fun setUp() {
        hiltRule.inject()
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = MockServerDispatcher().RequestDispatcher()
        mockWebServer.start(8080)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getRandomDetails_Success() = runBlocking {
        val response = pokemonApi.getPokemonDetailsAsync(1).await()
        val items = MockServerDispatcher().getJsonContent("responseInfo.json")
        val data = Gson().fromJson(items, Pokemon::class.java)
        assertThat(response).isEqualTo(data)
    }

    @Test
    fun getRandomDetails_fail() : Unit = runBlocking {
        try {
            pokemonApi.getPokemonDetailsAsync(2).await()
        } catch (e: HttpException) {
            assertThat(e.code()).isEqualTo(400)
        }
    }




}