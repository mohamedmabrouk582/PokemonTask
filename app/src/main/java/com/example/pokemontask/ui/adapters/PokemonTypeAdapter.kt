package com.example.pokemontask.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.PokemonType
import com.example.pokemontask.R
import com.example.pokemontask.databinding.TypeItemViewBinding


/**
 * @name Mohamed Mabrouk
 * Copyrights (c) 16/12/2021 created by Just clean
 */
class PokemonTypeAdapter :
    RecyclerView.Adapter<PokemonTypeAdapter.Holder>() {
    var items: ArrayList<PokemonType> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class Holder(private val viewBinding: TypeItemViewBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: PokemonType) {
            viewBinding.type = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.type_item_view,
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