package com.dd.hospitals.ui.section

import com.dd.domain.manager.ResourceManager
import com.dd.domain.model.RequestSectionModel
import com.dd.domain.model.SectionModel
import com.dd.domain.usecase.GetLocalSectionUseCase
import com.dd.hospitals.base.BaseToolbarsViewModel
import com.dd.hospitals.model.ToolbarModel
import com.dd.hospitals.ui.main.MainToolbarsViewModel
import com.dd.hospitals.ui.region.RegionState

class SectionViewModel(
        private val resourceManager: ResourceManager,
        private val getLocalSectionUseCase: GetLocalSectionUseCase
) : BaseToolbarsViewModel<SectionState, SectionNavigator.Navigation>() {
    /**
     * Constants
     */
    companion object {
        const val TELEGRAM_CLICKED = 1
    }

    /**
     * Default variables
     */
    override val initialViewState: SectionState = SectionState()
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
                    val responseCategoryModel = getLocalSectionUseCase.execute(RequestSectionModel())
                    updateToNormalState {
                        copy(
                                listSections = responseCategoryModel.list
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
    fun onActionCategoryClick(sectionModel: SectionModel) {
        navigate(
                SectionNavigator.Navigation.ToRegion(
                        RegionState(
                                sectionId = sectionModel.id,
                                sectionName = sectionModel.name
                        )
                )
        )
    }
}