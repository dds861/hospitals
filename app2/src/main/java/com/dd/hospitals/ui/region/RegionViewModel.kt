package com.dd.hospitals.ui.region

import com.dd.domain.manager.ResourceManager
import com.dd.domain.model.RegionModel
import com.dd.domain.model.RequestRegionModel
import com.dd.domain.usecase.GetLocalRegionUseCase
import com.dd.hospitals.base.BaseToolbarsViewModel
import com.dd.hospitals.model.ToolbarModel
import com.dd.hospitals.ui.hospital.HospitalState
import com.dd.hospitals.ui.main.MainToolbarsViewModel

class RegionViewModel(
        private val resourceManager: ResourceManager,
        private val getLocalRegionUseCase: GetLocalRegionUseCase
) : BaseToolbarsViewModel<RegionState, RegionNavigator.Navigation>() {

    /** Default variables*/
    override val initialViewState: RegionState = RegionState()

    /** Default functions*/
    override fun onConfigureToolbars(mainToolbarsVm: MainToolbarsViewModel) {
        checkDataState { state ->
            mainToolbarsVm.onActionUpdateToolbar {
                it.copy(
                        toolbarTitle = state.sectionName,
                        telegramButton = ToolbarModel.TelegramButton(
                                visibility = true
                        )
                )
            }
        }
    }

    override fun onStartFirstTime(statePreloaded: Boolean) {
        executeUseCaseWithException(
                {
                    val responseCategoryModel = getLocalRegionUseCase.execute(RequestRegionModel())
                    updateToNormalState {
                        copy(
                                listRegions = responseCategoryModel.list
                        )
                    }
                },
                { e ->
                    updateToErrorState(e)
                })
    }

    /**Custom functions*/
    fun onActionCategoryClick(regionModel: RegionModel) {
        checkDataState {
            navigate(
                    RegionNavigator.Navigation.Makal(
                            HospitalState(
                                    sectionId = it.sectionId,
                                    regionTitle = regionModel.name,
                                    regionId = regionModel.id
                            )
                    )
            )
        }
    }
}