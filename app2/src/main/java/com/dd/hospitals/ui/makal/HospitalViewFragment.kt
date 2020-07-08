package com.dd.hospitals.ui.makal

import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carmabs.ema.core.constants.STRING_EMPTY
import com.carmabs.ema.core.state.EmaExtraData
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.dd.hospitals.base.BaseToolbarsFragment
import com.dd.hospitals.ui.main.MainToolbarsViewModel
import kotlinx.android.synthetic.main.fragment_cards.*
import org.kodein.di.generic.instance

class HospitalViewFragment
    : BaseToolbarsFragment<HospitalState, HospitalViewModel, HospitalNavigator.Navigation>() {
    /**
     * Default variables
     */
    override val layoutId: Int = com.dd.hospitals.R.layout.fragment_cards
    override val navigator: HospitalNavigator by instance()
    override val viewModelSeed: HospitalViewModel by instance()

    /**
     * Custom variables
     */
    private lateinit var vm: HospitalViewModel

    /**
     * Default functions
     */
    override fun onInitializedWithToolbarsManagement(viewModel: HospitalViewModel, mainToolbarViewModel: MainToolbarsViewModel) {
        vm = viewModel
        setupRecycler()
    }

    override fun onSingleEvent(data: EmaExtraData) {
    }

    override fun onNormal(data: HospitalState) {
        loadRecyclerViews(data)
        setupListeners(data)
    }

    private fun setupListeners(data: HospitalState) {
        et_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                vm.onActionSearch(editable.toString())
            }
        })

        iv_makal_clear_search.setOnClickListener {
            YoYo.with(Techniques.FadeOut).duration(150).repeat(0).playOn(it)
            YoYo.with(Techniques.FadeIn).duration(350).repeat(0).playOn(it)

            et_search.setText(STRING_EMPTY)
        }
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
        rvMakals.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    private fun loadRecyclerViews(data: HospitalState) {
        rvMakals.adapter = data.listHospitals.toMutableList().let { it ->
            HospitalAdapter(requireContext(), listItems = it) {
                viewModelSeed.onActionItemClicked(it)
            }
        }
    }


}

