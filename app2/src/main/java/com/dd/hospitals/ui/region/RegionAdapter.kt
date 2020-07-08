package com.dd.hospitals.ui.region

import android.view.View
import com.carmabs.ema.android.ui.EmaRecyclerAdapter
import com.dd.domain.model.RegionModel
import kotlinx.android.synthetic.main.item_in_list.view.*


class RegionAdapter(override val listItems: MutableList<RegionModel> = mutableListOf(),
                    private val itemListener: (RegionModel) -> Unit) : EmaRecyclerAdapter<RegionModel>() {

    override val layoutItemId: Int = com.dd.hospitals.R.layout.item_in_list

    override fun View.bind(item: RegionModel, viewType: Int) {
        tvCategoryTitle.text = item.name

        tvCategoryTitle.setOnClickListener {
            itemListener.invoke(item)
        }
    }
}



