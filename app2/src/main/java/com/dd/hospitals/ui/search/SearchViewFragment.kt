package com.dd.hospitals.ui.search

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carmabs.ema.core.state.EmaExtraData
import com.dd.data.utils.KeyboardUtils
import com.dd.domain.model.HospitalModel
import com.dd.hospitals.base.BaseToolbarsFragment
import com.dd.hospitals.ui.main.MainToolbarsViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import org.kodein.di.generic.instance

class SearchViewFragment
    : BaseToolbarsFragment<SearchState, SearchViewModel, SearchNavigator.Navigation>() {
    /**
     * Default variables
     */
    override val layoutId: Int = com.dd.hospitals.R.layout.fragment_search
    override val navigator: SearchNavigator by instance()
    override val viewModelSeed: SearchViewModel by instance()

    /**
     * Custom variables
     */
    private lateinit var vm: SearchViewModel

    /**
     * Default functions
     */
    override fun onInitializedWithToolbarsManagement(viewModel: SearchViewModel, mainToolbarViewModel: MainToolbarsViewModel) {
        vm = viewModel
        setupRecycler()
    }

    override fun onSingleEvent(data: EmaExtraData) {
    }

    override fun onNormal(data: SearchState) {
        loadRecyclerViews(data)
    }

    override fun onAlternative(data: EmaExtraData) {
    }

    override fun onError(error: Throwable) {}

    override fun onSingle(data: EmaExtraData) {
    }

    /**
     * Custom functions
     */
    private fun setupRecycler() {
        rvSearch.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    private fun loadRecyclerViews(data: SearchState) {
        data.listHospitals.toMutableList().let { mutableList ->
            loadAdapter(data, mutableList)
        }
    }

    private fun loadAdapter(data: SearchState, mutableList: MutableList<HospitalModel>) {
        rvSearch.adapter = SearchAdapter(context = requireContext(), listItems = mutableList, adapterType = data.adapterType) { model ->
            when (data.adapterType) {
                SearchState.AdapterType.HINT -> loadHints(model)

                SearchState.AdapterType.MAKALS -> {
                }
            }
        }
    }

    private fun loadHints(model: HospitalModel) {
        model.address?.let { vm.getHospitalsByQueryText(it) }
        activity?.let { KeyboardUtils.hideKeyboard(it, view) }
    }
}

