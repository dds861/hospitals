package com.dd.hospitals.ui.section

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carmabs.ema.core.state.EmaExtraData
import com.dd.hospitals.base.BaseToolbarsFragment
import com.dd.hospitals.ui.main.MainToolbarsViewModel
import kotlinx.android.synthetic.main.fragment_category.*
import org.kodein.di.generic.instance

class SectionViewFragment : BaseToolbarsFragment<SectionState, SectionViewModel, SectionNavigator.Navigation>() {
    /**
     * Default variables
     */
    override val layoutId: Int = com.dd.hospitals.R.layout.fragment_category
    override val navigator: SectionNavigator by instance()
    override val viewModelSeed: SectionViewModel by instance()

    /**
     * Custom variables
     */
    private lateinit var vm: SectionViewModel

    /**
     * Default functions
     */
    override fun onInitializedWithToolbarsManagement(viewModel: SectionViewModel, mainToolbarViewModel: MainToolbarsViewModel) {
        vm = viewModel
        setupRecycler()
    }

    override fun onSingleEvent(data: EmaExtraData) {
    }

    override fun onNormal(data: SectionState) {
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

    private fun loadRecyclerViews(data: SectionState) {
        rvCategory.adapter = data.listSections.toMutableList().let { it ->
            SectionAdapter(listItems = it) {
                vm.onActionCategoryClick(it)
            }
        }
    }
}
