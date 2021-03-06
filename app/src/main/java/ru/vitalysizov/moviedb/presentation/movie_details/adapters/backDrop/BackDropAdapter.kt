package ru.vitalysizov.moviedb.presentation.movie_details.adapters.backDrop


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.vitalysizov.moviedb.databinding.ItemBackdropMovieDetailsBinding
import ru.vitalysizov.moviedb.model.domain.images.ImageItem
import ru.vitalysizov.moviedb.presentation.base.adapter.viewHolder.BaseViewHolder

class BackDropAdapter : ListAdapter<ImageItem, BaseViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<ImageItem>() {
        override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
            return oldItem.filePath == newItem.filePath
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBackdropMovieDetailsBinding.inflate(inflater, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentImage = getItem(position)
        val itemBinding = holder.binding as ItemBackdropMovieDetailsBinding

        itemBinding.image = currentImage
        itemBinding.executePendingBindings()
    }

}