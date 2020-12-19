package ru.vitalysizov.moviedb.presentation.person_details.adapters.cast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.databinding.ItemPersonMovieBinding
import ru.vitalysizov.moviedb.databinding.ItemPersonTvBinding
import ru.vitalysizov.moviedb.model.domain.person.PersonCastMovieItem
import ru.vitalysizov.moviedb.model.domain.person.PersonCastTvItem
import ru.vitalysizov.moviedb.utils.executeAfter

class PersonCastAndCrewAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val onMovieClickListener:(item: PersonCastMovieItem) -> Unit,
    private val onTvShowClickListener:(item: PersonCastTvItem) -> Unit
) : ListAdapter<Any, PersonCastAndCrewViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldcastIem: Any, newCastItem: Any): Boolean {
            return when {
                oldcastIem is PersonCastMovieItem && newCastItem is PersonCastMovieItem -> oldcastIem == newCastItem
                oldcastIem is PersonCastTvItem && newCastItem is PersonCastTvItem -> oldcastIem == newCastItem
                else -> false
            }
        }

        override fun areContentsTheSame(oldcastIem: Any, newCastItem: Any): Boolean {
            return when {
                oldcastIem is PersonCastMovieItem && newCastItem is PersonCastMovieItem -> oldcastIem.id == newCastItem.id
                oldcastIem is PersonCastTvItem && newCastItem is PersonCastTvItem -> oldcastIem.id == newCastItem.id
                else -> false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonCastAndCrewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_person_movie -> PersonCastAndCrewViewHolder.PersonCastMovieViewHolder(
                ItemPersonMovieBinding.inflate(inflater, parent, false)
            )
            R.layout.item_person_tv -> PersonCastAndCrewViewHolder.PersonCastTvViewHolder(
                ItemPersonTvBinding.inflate(inflater, parent, false)
            )

            else -> throw IllegalStateException("Unknown view type: $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is PersonCastMovieItem -> R.layout.item_person_movie
            is PersonCastTvItem -> R.layout.item_person_tv
            else -> throw IllegalStateException("Unknown view type at position: $position")
        }
    }

    override fun onBindViewHolder(holder: PersonCastAndCrewViewHolder, position: Int) {
        when (holder) {
            is PersonCastAndCrewViewHolder.PersonCastMovieViewHolder -> holder.binding.executeAfter {
                val currentItem = getItem(position) as PersonCastMovieItem
                personCastMovieItem = currentItem
                root.setOnClickListener {  onMovieClickListener.invoke(currentItem) }
                lifecycleOwner = this@PersonCastAndCrewAdapter.lifecycleOwner
            }
            is PersonCastAndCrewViewHolder.PersonCastTvViewHolder -> holder.binding.executeAfter {
                val currentItem = getItem(position) as PersonCastTvItem
                root.setOnClickListener {  onTvShowClickListener.invoke(currentItem) }
                personCastTvItem = currentItem
                lifecycleOwner = this@PersonCastAndCrewAdapter.lifecycleOwner
            }
        }
    }

}

sealed class PersonCastAndCrewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    class PersonCastMovieViewHolder(val binding: ItemPersonMovieBinding) :
        PersonCastAndCrewViewHolder(binding.root)

    class PersonCastTvViewHolder(val binding: ItemPersonTvBinding) :
        PersonCastAndCrewViewHolder(binding.root)

}