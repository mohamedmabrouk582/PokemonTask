package com.example.pokemontask.ui.adapters

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.PokemonStatistics
import com.example.pokemontask.R
import com.example.pokemontask.databinding.PokemonStatItemViewBinding


/**
 * @name Mohamed Mabrouk
 * Copyrights (c) 16/12/2021 created by Just clean
 */
class PokemonStatAdapter :
    RecyclerView.Adapter<PokemonStatAdapter.Holder>() {
    var items: ArrayList<PokemonStatistics> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class Holder(private val viewBinding: PokemonStatItemViewBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: PokemonStatistics) {
            viewBinding.stat = item
            viewBinding.progressBar.apply {
                setProgress(item.base_stat,true)
                progressDrawable?.setColorFilter(
                    Color.parseColor(
                        when(item.base_stat){
                            in (0 .. 29) -> "#fb6c6c"
                            in (30 .. 59) -> "#f47932"
                            in (60 .. 89) -> "#91cafe"
                            in (90 .. 100) -> "#63c88c"
                            else -> "#fb6c6c"
                        }
                    ), android.graphics.PorterDuff.Mode.SRC_IN)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.pokemon_stat_item_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}