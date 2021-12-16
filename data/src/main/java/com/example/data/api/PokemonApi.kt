package com.example.data.api

import com.example.domain.models.Pokemon
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @name Mohamed Mabrouk
 * Copyrights (c) 16/12/2021 created by Just clean
 */
interface PokemonApi {

    @GET("pokemon/{id}")
    fun getPokemonDetailsAsync(@Path("id") id:Int) : Deferred<Pokemon>

}