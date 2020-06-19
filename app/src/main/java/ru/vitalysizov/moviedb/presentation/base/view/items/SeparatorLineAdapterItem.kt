package ru.vitalysizov.moviedb.presentation.base.view.items

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import ru.vitalysizov.moviedb.R

class SeparatorLineAdapterItem : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) { }

    override fun getLayout(): Int = R.layout.item_separator_line
}