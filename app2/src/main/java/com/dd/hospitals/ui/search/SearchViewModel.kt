package com.dd.hospitals.ui.search

import com.dd.domain.manager.ResourceManager
import com.dd.domain.model.HospitalModel
import com.dd.domain.model.RequestHospitalModel
import com.dd.domain.usecase.GetLocalHospitalByQueryTextUseCase
import com.dd.domain.usecase.GetLocalHospitalBySectionIdAndRegionIdUseCase
import com.dd.hospitals.base.BaseToolbarsViewModel
import com.dd.hospitals.model.ToolbarModel
import com.dd.hospitals.ui.main.MainToolbarsViewModel
import java.util.*

class SearchViewModel(
        val resourceManager: ResourceManager,
        private val localHospitalBySectionIdAndRegionIdUseCase: GetLocalHospitalBySectionIdAndRegionIdUseCase,
        private val localHospitalByQueryTextUseCase: GetLocalHospitalByQueryTextUseCase
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
                        val responseMakalModel =
                                localHospitalBySectionIdAndRegionIdUseCase.execute(
                                        RequestHospitalModel(
                                                sectionId = it.categoryId,
                                                regionId = it.regionId
                                        )
                                )
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
                                    filterHintsByText(it)
                                }
                        )
                )
            }
        }
    }

    /**
     * Custom functions
     */
    private fun filterHintsByText(queryText: String) {
        checkDataState {
            executeUseCase {
                val response = localHospitalByQueryTextUseCase.execute(RequestHospitalModel(queryText = queryText))
                updateToNormalState {
                    copy(
                            listHospitals = filterToolbarHintsByQueryText(queryText, response.list),
                            adapterType = SearchState.AdapterType.HINT
                    )
                }
            }
        }
    }

    fun getHospitalsByQueryText(queryText: String) {
//        mainToolbarsVm.onActionShowInterstitialAd()
        mainToolbarsVm.onActionSearchViewText(queryText)
        checkDataState {
            executeUseCaseWithException(
                    {
                        val responseMakalModel = localHospitalByQueryTextUseCase.execute(RequestHospitalModel(queryText = queryText))
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