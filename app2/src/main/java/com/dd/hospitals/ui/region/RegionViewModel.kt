package com.dd.hospitals.ui.region

import com.dd.domain.manager.ResourceManager
import com.dd.domain.model.RegionModel
import com.dd.domain.model.RequestRegionModel
import com.dd.domain.usecase.GetLocalRegionUseCase
import com.dd.hospitals.base.BaseToolbarsViewModel
import com.dd.hospitals.model.ToolbarModel
import com.dd.hospitals.ui.main.MainToolbarsViewModel
import com.dd.hospitals.ui.makal.HospitalState

class RegionViewModel(
        private val resourceManager: ResourceManager,
        private val getLocalRegionUseCase: GetLocalRegionUseCase
) : BaseToolbarsViewModel<RegionState, RegionNavigator.Navigation>() {
    /**
     * Constants
     */
    companion object {
        const val TELEGRAM_CLICKED = 1
    }

    /**
     * Default variables
     */
    override val initialViewState: RegionState = RegionState()
    /**
     * Custom variables
     */
    /**
     * Default functions
     */
    override fun onConfigureToolbars(mainToolbarsVm: MainToolbarsViewModel) {
        mainToolbarsVm.onActionUpdateToolbar {
            it.copy(
                    toolbarTitle = resourceManager.getToolbarTitle(),
                    toolbarTitleVisibility = true,
                    toolbarLogoOrBackVisibility = true,
                    telegramButton = ToolbarModel.TelegramButton(
                            visibility = true
                    ),
                    searchButton = ToolbarModel.SearchButton(
                            visibility = true
                    )
            )
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

    /**
     * Custom functions
     */
    fun onActionCategoryClick(regionModel: RegionModel) {
        navigate(
                RegionNavigator.Navigation.Makal(
                        HospitalState(
                                categoryId = regionModel.id,
                                categoryTitle = regionModel.name
                        )
                )
        )
    }
}