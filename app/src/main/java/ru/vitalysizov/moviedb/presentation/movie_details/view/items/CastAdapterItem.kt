package ru.vitalysizov.moviedb.presentation.movie_details.view.items

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_cast.view.*
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.model.domain.castAndCrew.CastItem
import ru.vitalysizov.moviedb.utils.loadCastPhoto

class CastAdapterItem(private val castItem: CastItem, private val actionDetailsCast: (id: Int) -> Unit) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            ivCastPhoto.loadCastPhoto(castItem.profilePath)

            val castName = castItem.name
            val castCharacter = castItem.character

            val spanText =
                SpannableString(resources.getString(R.string.cast_mask, castName, castCharacter))
                    .apply {
                        setSpan(
                            ForegroundColorSpan(Color.BLACK),
                            0,
                            castName.length,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                        setSpan(
                            StyleSpan(Typeface.BOLD),
                            0,
                            castName.length,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }

            tvCast.text = spanText
            setOnClickListener { actionDetailsCast.invoke(castItem.id) }
        }
    }

    override fun getLayout(): Int = R.layout.item_cast
}