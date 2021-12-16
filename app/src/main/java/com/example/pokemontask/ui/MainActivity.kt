package com.example.pokemontask.ui

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.pokemontask.R
import com.example.pokemontask.databinding.ActivityMainBinding
import com.example.pokemontask.intent.PokemonStates
import com.example.pokemontask.ui.adapters.PagerAdapter
import com.example.pokemontask.ui.adapters.PokemonTypeAdapter
import com.example.pokemontask.ui.fragments.PokemonMovesFragment
import com.example.pokemontask.ui.fragments.PokemonStatisticsFragment
import com.example.pokemontask.utils.PokemonColorUtil
import com.example.pokemontask.viewmodels.PokemonViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding
    private val viewModel: PokemonViewModel by viewModels()
    private val pagerAdapter by lazy { PagerAdapter(this) }

    private val adapter by lazy { PokemonTypeAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewBinding.typeRcv.adapter = adapter
        viewBinding.viewPager.adapter = pagerAdapter
        viewModel.getRandomPokemon()
        val fr: ArrayList<Fragment> = arrayListOf(
            PokemonStatisticsFragment(viewModel),
            PokemonMovesFragment(viewModel)
        )
        pagerAdapter.fragments = fr
        handleStates()
        val tabs = resources.getStringArray(R.array.tabs)
        TabLayoutMediator(viewBinding.tabs, viewBinding.viewPager) { tab, position ->
            tab.text = tabs[position]
        }.attach()
    }

    private fun handleStates() {
        lifecycleScope.launch {
            viewModel.pokemonStates.collect {
                when (it) {
                    PokemonStates.Loading -> viewBinding.progress.visibility = View.VISIBLE
                    is PokemonStates.Error -> {
                        viewBinding.progress.visibility = View.GONE
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_SHORT).show()
                    }
                    is PokemonStates.LoadPokemonDetails -> {
                        viewBinding.progress.visibility = View.GONE
                        adapter.items = it.pokemon.types
                        viewBinding.pokemon = it.pokemon
                        val color =
                            PokemonColorUtil(applicationContext).getPokemonColor(it.pokemon.types.map { it.type.name })
                        viewBinding.appBar.setBackgroundColor(color)
                        viewBinding.toolbarLayout.contentScrim?.colorFilter =
                            PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                        window?.statusBarColor = color
                    }
                }
            }
        }
    }

    fun getRandom(view: android.view.View) {
        viewModel.getRandomPokemon()
    }
}