package com.dd.hospitals.ui.section

import com.carmabs.ema.core.constants.STRING_EMPTY
import com.carmabs.ema.core.state.EmaBaseState
import com.dd.domain.model.SectionModel

data class SectionState(
        val default: String = STRING_EMPTY,
        val listSections: List<SectionModel> = listOf()
) : EmaBaseState