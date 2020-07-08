package com.dd.hospitals.ui.section

import android.view.View
import com.carmabs.ema.android.ui.EmaRecyclerAdapter
import com.dd.domain.model.SectionModel
import kotlinx.android.synthetic.main.item_in_list.view.*


class SectionAdapter(override val listItems: MutableList<SectionModel> = mutableListOf(),
                    private val itemListener: (SectionModel) -> Unit) : EmaRecyclerAdapter<SectionModel>() {

    override val layoutItemId: Int = com.dd.hospitals.R.layout.item_in_list

    override fun View.bind(item: SectionModel, viewType: Int) {
        tvCategoryTitle.text = item.name

        tvCategoryTitle.setOnClickListener {
            itemListener.invoke(item)
        }
    }
}



