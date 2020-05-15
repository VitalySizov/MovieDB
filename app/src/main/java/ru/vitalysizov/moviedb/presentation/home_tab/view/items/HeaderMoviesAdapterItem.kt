package ru.vitalysizov.moviedb.presentation.home_tab.view.items

import android.widget.RadioButton
import android.widget.TextView
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_header_movie.view.*
import ru.vitalysizov.moviedb.R

class HeaderMoviesAdapterItem(
    private val headerTitle: String,
    private val changeListAction: (id: Int) -> Unit,
    private val firstNameRadioButton: String,
    private val secondNameRadioButton: String
) : Item() {

    companion object {
        const val CHANGE_TYPE = "CHANGE_TYPE"
    }

    private var headerType: HeaderType = HeaderType.Empty

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            bindHeaderName(tv_header_title, headerTitle)
            bindRadioButtonsName(rb_first, rb_second)
            rb_first.isChecked = true
            rb_header.setOnCheckedChangeListener { _, checkedId -> changeListAction.invoke(checkedId) }
        }
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int, payload: List<Any>) {
        if (payload.contains(CHANGE_TYPE)) {
            bindChangeList(viewHolder)
        } else {
            bind(viewHolder, position)
        }
    }

    fun setHeaderType(type: HeaderType) {
        headerType = type
    }

    override fun getLayout(): Int = R.layout.item_header_movie

    private fun bindHeaderName(textView: TextView, headerTitle: String) {
        textView.text = headerTitle
    }

    private fun bindRadioButtonsName(rbFirst: RadioButton, rbSecond: RadioButton) {
        rbFirst.text = firstNameRadioButton
        rbSecond.text = secondNameRadioButton
    }

    private fun bindChangeList(holder: GroupieViewHolder) {
        val view = holder.itemView
        when (headerType) {
            HeaderType.InTheaters -> {
                bindHeaderName(
                    view.tv_header_title,
                    view.resources.getString(R.string.now_in_theaters_header)
                )
                view.rb_first.isChecked = true
            }
            HeaderType.Populars -> {
                bindHeaderName(
                    view.tv_header_title,
                    view.resources.getString(R.string.populars_header)
                )
                view.rb_second.isChecked = true
            }

            HeaderType.Empty -> {
            }
        }
    }

    enum class HeaderType {
        Empty, InTheaters, Populars
    }

}