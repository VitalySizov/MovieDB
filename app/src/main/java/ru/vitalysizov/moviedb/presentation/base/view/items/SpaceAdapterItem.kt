package ru.vitalysizov.moviedb.presentation.base.view.items

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import ru.vitalysizov.moviedb.R

class SpaceAdapterItem : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {}

    override fun getLayout(): Int = R.layout.item_space
}