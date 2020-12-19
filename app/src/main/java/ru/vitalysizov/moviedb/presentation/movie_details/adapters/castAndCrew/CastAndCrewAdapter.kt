package ru.vitalysizov.moviedb.presentation.movie_details.adapters.castAndCrew

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.vitalysizov.moviedb.databinding.ItemCastBinding
import ru.vitalysizov.moviedb.model.domain.castAndCrew.CastItem
import ru.vitalysizov.moviedb.presentation.base.ItemClickListener
import ru.vitalysizov.moviedb.presentation.base.adapter.viewHolder.BaseViewHolder

class CastAndCrewAdapter(
    private val listener: ItemClickListener<CastItem>
) : ListAdapter<CastItem, BaseViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<CastItem>() {
        override fun areItemsTheSame(oldItem: CastItem, newItem: CastItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CastItem, newItem: CastItem): Boolean {
            return oldItem.name == newItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCastBinding.inflate(inflater, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = getItem(position)
        val biding = holder.binding as ItemCastBinding
        biding.root.setOnClickListener { listener.onClickListener(currentItem) }

        biding.castItem = currentItem
        biding.executePendingBindings()
    }
}