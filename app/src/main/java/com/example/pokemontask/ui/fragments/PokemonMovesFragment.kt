package com.example.pokemontask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.domain.models.Pokemon
import com.example.pokemontask.R
import com.example.pokemontask.databinding.MovesFrgamentsBinding
import com.example.pokemontask.intent.PokemonStates
import com.example.pokemontask.ui.adapters.PokemonMoveAdapter
import com.example.pokemontask.ui.adapters.PokemonStatAdapter
import com.example.pokemontask.utils.PokemonColorUtil
import com.example.pokemontask.viewmodels.PokemonViewModel
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * @name Mohamed Mabrouk
 * Copyrights (c) 16/12/2021 created by Just clean
 */
@AndroidEntryPoint
class PokemonMovesFragment(val viewModel: PokemonViewModel) : Fragment() {
    lateinit var viewBinding: MovesFrgamentsBinding
    private val adapter by lazy { PokemonMoveAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<MovesFrgamentsBinding>(inflater,R.layout.moves_frgaments,container,false).apply {
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = DataBindingUtil.findBinding(view)!!
        viewBinding.statRcv.layoutManager = FlexboxLayoutManager(requireContext()).apply {
            flexDirection = FlexDirection.ROW
        }
        viewBinding.statRcv.adapter = adapter

        lifecycleScope.launch {
            viewModel.pokemonStates.collect {
                if (it is PokemonStates.LoadPokemonDetails){
                    val color = PokemonColorUtil(requireContext()).getPokemonColor(it.pokemon.types.map { it.type.name })
                    adapter.color = color
                    adapter.items = it.pokemon.moves
                }
            }
        }

    }
}