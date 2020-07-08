package com.dd.hospitals.ui.region

import com.carmabs.ema.core.constants.INT_ZERO
import com.carmabs.ema.core.constants.STRING_EMPTY
import com.carmabs.ema.core.state.EmaBaseState
import com.dd.domain.model.RegionModel

data class RegionState(
        val sectionId: Int = INT_ZERO,
        val listRegions: List<RegionModel> = listOf(),
        val sectionName: String = STRING_EMPTY
) : EmaBaseState