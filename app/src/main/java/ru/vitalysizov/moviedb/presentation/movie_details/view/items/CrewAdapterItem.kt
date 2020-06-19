package ru.vitalysizov.moviedb.presentation.movie_details.view.items

import androidx.annotation.StringRes
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_crew.view.*
import ru.vitalysizov.moviedb.R

class CrewAdapterItem(
    @StringRes val departmentName: Int,
    private val crewNames: String,
    private val crewId: List<Int>,
    private val actionDetailsCrew: (ids: List<Int>) -> Unit
) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            tvDepartmentCrew.text = resources.getString(departmentName)
            tvCrewNames.text = crewNames
            setOnClickListener { actionDetailsCrew.invoke(crewId) }
        }
    }

    override fun getLayout(): Int = R.layout.item_crew
}