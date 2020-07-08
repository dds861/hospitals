package com.dd.hospitals.ui.region

import com.carmabs.ema.core.constants.INT_ZERO
import com.carmabs.ema.core.state.EmaBaseState
import com.dd.domain.model.RegionModel

data class RegionState(
        val sectionIdSelected: Int = INT_ZERO,
        val listRegions: List<RegionModel> = listOf()
) : EmaBaseState