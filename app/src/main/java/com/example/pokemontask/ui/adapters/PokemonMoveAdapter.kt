package com.example.pokemontask.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.PokemonMove
import com.example.pokemontask.R
import com.example.pokemontask.databinding.MoveItemViewBinding


/**
 * @name Mohamed Mabrouk
 * Copyrights (c) 16/12/2021 created by Just clean
 */
class PokemonMoveAdapter:
    RecyclerView.Adapter<PokemonMoveAdapter.Holder>() {
    var color : Int = Color.CYAN
    var items: ArrayList<PokemonMove> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class Holder(private val viewBinding: MoveItemViewBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: PokemonMove) {
            viewBinding.move = item
            viewBinding.card.setCardBackgroundColor(color)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.move_item_view,
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