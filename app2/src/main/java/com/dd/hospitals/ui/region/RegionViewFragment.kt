package com.dd.hospitals.ui.region

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carmabs.ema.core.state.EmaExtraData
import com.dd.hospitals.base.BaseToolbarsFragment
import com.dd.hospitals.ui.main.MainToolbarsViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import org.kodein.di.generic.instance

class RegionViewFragment : BaseToolbarsFragment<RegionState, RegionViewModel, RegionNavigator.Navigation>() {
    /**
     * Default variables
     */
    override val layoutId: Int = com.dd.hospitals.R.layout.fragment_list
    override val navigator: RegionNavigator by instance()
    override val viewModelSeed: RegionViewModel by instance()

    /**
     * Custom variables
     */
    private lateinit var vm: RegionViewModel

    /**
     * Default functions
     */
    override fun onInitializedWithToolbarsManagement(viewModel: RegionViewModel, mainToolbarViewModel: MainToolbarsViewModel) {
        vm = viewModel
        setupRecycler()
    }

    override fun onSingleEvent(data: EmaExtraData) {
    }

    override fun onNormal(data: RegionState) {
        loadRecyclerViews(data)
    }

    override fun onAlternative(data: EmaExtraData) {
    }

    override fun onError(error: Throwable) {
    }

    override fun onSingle(data: EmaExtraData) {
    }

    /**
     * Custom functions
     */
    private fun setupRecycler() {
        rvCategory.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    private fun loadRecyclerViews(data: RegionState) {
        rvCategory.adapter = data.listRegions.toMutableList().let { it ->
            RegionAdapter(listItems = it) {
                vm.onActionCategoryClick(it)
            }
        }
    }
}
