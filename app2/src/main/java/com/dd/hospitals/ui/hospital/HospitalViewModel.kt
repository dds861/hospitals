package com.dd.hospitals.ui.hospital

import com.dd.domain.manager.ResourceManager
import com.dd.domain.model.HospitalModel
import com.dd.domain.model.RequestHospitalModel
import com.dd.domain.usecase.GetLocalHospitalByCategoryIdUseCase
import com.dd.hospitals.base.BaseToolbarsViewModel
import com.dd.hospitals.model.ToolbarModel
import com.dd.hospitals.ui.main.MainToolbarsViewModel
import java.util.*

class HospitalViewModel(
        val resourceManager: ResourceManager,
        private val getLocalHospitalByCategoryIdUseCase: GetLocalHospitalByCategoryIdUseCase
) : BaseToolbarsViewModel<HospitalState, HospitalNavigator.Navigation>() {
    /**
     * Default variables
     */
    override val initialViewState: HospitalState = HospitalState()
    /**
     * Custom variables
     */
    /**
     * Default functions
     */
    override fun onStartFirstTime(statePreloaded: Boolean) {
        checkDataState {
            executeUseCaseWithException(
                    {
                        val responseMakalModel = getLocalHospitalByCategoryIdUseCase.execute(RequestHospitalModel(hospitalId = it.categoryId))
                        updateToNormalState {
                            copy(
                                    listHospitals = responseMakalModel.list
                            )
                        }
                    },
                    { e ->
                        updateToErrorState(e)
                    })
        }
    }

    override fun onConfigureToolbars(mainToolbarsVm: MainToolbarsViewModel) {
        checkDataState { makalState ->
            mainToolbarsVm.onActionUpdateToolbar {
                it.copy(
                        toolbarTitle = makalState.categoryTitle,
                        telegramButton = ToolbarModel.TelegramButton(
                                visibility = true
                        ),
                        searchButton = ToolbarModel.SearchButton(
                                visibility = true
                        )
                )
            }
        }
    }

    /**
     * Custom functions
     */
    fun onActionItemClicked(hospitalModel: HospitalModel) {
    }

    fun onActionSearch(queryText: String) {
        checkDataState {
            executeUseCaseWithException(
                    {
                        val responseMakalModel = getLocalHospitalByCategoryIdUseCase.execute(RequestHospitalModel(hospitalId = it.categoryId))

                        updateToNormalState {
                            copy(
                                    listHospitals = responseMakalModel.list.filter {
                                        it.branch
                                                .toLowerCase(Locale.ROOT)
                                                .contains(queryText.toLowerCase(Locale.ROOT))
                                    }
                            )
                        }
                    },
                    { e ->
                        updateToErrorState(e)
                    })
        }
    }
}