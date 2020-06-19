package ru.vitalysizov.moviedb.presentation.base.view.items

import androidx.annotation.StringRes
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_header_with_show_all.view.*
import ru.vitalysizov.moviedb.R

class HeaderWithShowAllAdapterItem(
    @StringRes val headerTitle: Int,
    @StringRes val showAll: Int,
    private val actionShowAll: () -> Unit
) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            tvHeaderTitle.text = resources.getString(headerTitle)
            tvShowAll.text = resources.getString(showAll)
            setOnClickListener { actionShowAll.invoke() }
        }
    }

    override fun getLayout(): Int = R.layout.item_header_with_show_all
}