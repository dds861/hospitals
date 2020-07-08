package com.dd.hospitals.ui.search

import com.dd.hospitals.base.BaseToolbarsViewModel
import com.dd.hospitals.model.ToolbarModel
import com.dd.hospitals.ui.main.MainToolbarsViewModel
import com.dd.domain.manager.ResourceManager
import com.dd.domain.model.HospitalModel
import com.dd.domain.model.RequestHospitalModel
import com.dd.domain.usecase.GetLocalHospitalByCategoryIdUseCase
import com.dd.domain.usecase.GetLocalHospitalByQueryTextUseCase
import java.util.*

class SearchViewModel(
        val resourceManager: ResourceManager,
        private val getLocalHospitalByCategoryIdUseCase: GetLocalHospitalByCategoryIdUseCase,
        private val getLocalHospitalByQueryTextUseCase: GetLocalHospitalByQueryTextUseCase
) : BaseToolbarsViewModel<SearchState, SearchNavigator.Navigation>() {
    /**
     * Default variables
     */
    override val initialViewState: SearchState = SearchState()
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
            mainToolbarsVm.onActionUpdateToolbar { toolbarModel ->
                toolbarModel.copy(
                        toolbarTitle = makalState.categoryTitle,
                        telegramButton = ToolbarModel.TelegramButton(
                                visibility = true
                        ),
                        searchButton = ToolbarModel.SearchButton(
                                visibility = true,
                                setOnQueryTextFocusChangeListener = {
                                    onActionFilterToolbarHintsByQueryText(it)
                                }
                        )
                )
            }
        }
    }

    /**
     * Custom functions
     */
    private fun onActionFilterToolbarHintsByQueryText(queryText: String) {
        //load results of hints by keyword
        checkDataState {
            executeUseCaseWithException(
                    {
                        val responseMakalModel = getLocalHospitalByQueryTextUseCase.execute(RequestHospitalModel(queryText = queryText))
                        updateToNormalState {
                            copy(
                                    listHospitals = filterToolbarHintsByQueryText(queryText, responseMakalModel.list),
                                    adapterType = SearchState.AdapterType.HINT
                            )
                        }
                    },
                    { e ->
                        updateToErrorState(e)
                    })
        }
    }

    fun onActionFilterToolbarMakalsByQueryText(queryText: String) {
        mainToolbarsVm.onActionShowInterstitialAd()
        //update text on searchView
        mainToolbarsVm.onActionSearchViewText(queryText)
        //load makals to recyclerview
        checkDataState {
            executeUseCaseWithException(
                    {
                        val responseMakalModel = getLocalHospitalByQueryTextUseCase.execute(RequestHospitalModel(queryText = queryText))
                        updateToNormalState {
                            copy(
                                    listHospitals = filterToolbarMakalsByQueryText(queryText, responseMakalModel.list),
//                                    listMakals = responseMakalModel.list,
                                    adapterType = SearchState.AdapterType.MAKALS
                            )
                        }
                    },
                    { e ->
                        updateToErrorState(e)
                    })
        }
    }

    private fun filterToolbarHintsByQueryText(queryText: String, list: List<HospitalModel>): List<HospitalModel> {
        if (queryText.isNotEmpty()) {
            val listHospitalModels: MutableList<HospitalModel> = mutableListOf()

            list.map { makalModel ->
                makalModel.address?.toLowerCase(Locale.ROOT)
                        ?.replace("\n", " ")
                        ?.replace(",", "")
                        ?.replace(".", "")
                        ?.replace(" - ", "")
                        ?.replace("- ", "")
                        ?.replace(" -", "")
                        ?.split(" ")
                        ?.filter { s -> s.contains(queryText) }
                        ?.map { HospitalModel(address = it) }
            }.map { listMakals ->
                listMakals?.map {
                    listHospitalModels.add(it)
                }
            }

            return listHospitalModels.distinct()
                    .sortedByDescending { it.address }
                    .sortedBy { it.address?.length }
        } else {
            return listOf()
        }
    }

    private fun filterToolbarMakalsByQueryText(queryText: String, list: List<HospitalModel>): List<HospitalModel> {
        return if (queryText.isNotEmpty()) {
            val listHospitalModels: MutableList<HospitalModel> = mutableListOf()

            list.map { makalModel ->
                val addressText = makalModel.address
                val indexBeforeQueryText = addressText?.toLowerCase()?.indexOf(queryText)
                val address = indexBeforeQueryText?.minus(1)?.let {
                    makalModel.address?.get(it)
                }
                if (indexBeforeQueryText != null) {
                    if (indexBeforeQueryText < 0 || (indexBeforeQueryText >= 0 && address == ' ')) {
                        listHospitalModels.add(makalModel)
                    }
                }
            }

            listHospitalModels.distinct()
                    .sortedByDescending { it.address }
                    .sortedBy { it.address?.length }
        } else {
            listOf()
        }
    }
}