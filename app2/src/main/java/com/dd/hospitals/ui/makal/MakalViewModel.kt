package com.dd.hospitals.ui.makal

import com.dd.domain.manager.ResourceManager
import com.dd.domain.model.MakalModel
import com.dd.domain.model.RequestMakalModel
import com.dd.domain.usecase.GetLocalMakalByCategoryIdUseCase
import com.dd.hospitals.base.BaseToolbarsViewModel
import com.dd.hospitals.model.ToolbarModel
import com.dd.hospitals.ui.main.MainToolbarsViewModel
import java.util.*

class MakalViewModel(
        val resourceManager: ResourceManager,
        private val getLocalMakalByCategoryIdUseCase: GetLocalMakalByCategoryIdUseCase
) : BaseToolbarsViewModel<MakalState, MakalNavigator.Navigation>() {
    /**
     * Default variables
     */
    override val initialViewState: MakalState = MakalState()
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
                        val responseMakalModel = getLocalMakalByCategoryIdUseCase.execute(RequestMakalModel(categoryId = it.categoryId))
                        updateToNormalState {
                            copy(
                                    listMakals = responseMakalModel.list
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
    fun onActionItemClicked(makalModel: MakalModel) {
    }

    fun onActionSearch(queryText: String) {
        checkDataState {
            executeUseCaseWithException(
                    {
                        val responseMakalModel = getLocalMakalByCategoryIdUseCase.execute(RequestMakalModel(categoryId = it.categoryId))

                        updateToNormalState {
                            copy(
                                    listMakals = responseMakalModel.list.filter {
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