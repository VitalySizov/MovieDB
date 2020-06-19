package ru.vitalysizov.moviedb.presentation.base.view.items

import androidx.annotation.StringRes
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_header_genres.view.*
import ru.vitalysizov.moviedb.R

class HeaderAdapterItem(@StringRes private val header: Int) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            tv_header_title.text = resources.getString(header)
        }
    }

    override fun getLayout(): Int = R.layout.item_header_genres
}