package ru.vitalysizov.moviedb.presentation.movie_details.adapters.castAndCrew


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.vitalysizov.moviedb.databinding.ItemCarouselCastBinding
import ru.vitalysizov.moviedb.model.domain.castAndCrew.CastAndCrewItem
import ru.vitalysizov.moviedb.model.domain.castAndCrew.CastItem
import ru.vitalysizov.moviedb.presentation.base.ItemClickListener
import ru.vitalysizov.moviedb.presentation.base.adapter.viewHolder.BaseViewHolder

class CarouselCastAndCrewAdapter(
    private val listener: ItemClickListener<CastItem>
) : ListAdapter<CastAndCrewItem, BaseViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<CastAndCrewItem>() {
        override fun areItemsTheSame(oldItem: CastAndCrewItem, newItem: CastAndCrewItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CastAndCrewItem,
            newItem: CastAndCrewItem
        ): Boolean {
            return oldItem.cast == newItem.cast
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCarouselCastBinding.inflate(inflater, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = getItem(position)
        val binding = holder.binding as ItemCarouselCastBinding
        binding.listener = listener
        binding.castAndCrew = currentItem
    }

}