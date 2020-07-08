package com.dd.domain.model

import com.carmabs.ema.core.constants.INT_ZERO
import com.carmabs.ema.core.constants.STRING_EMPTY
import com.carmabs.ema.core.state.EmaModel

data class ResponseRegionModel(
        val result: String = STRING_EMPTY,
        val list: List<RegionModel> = listOf()
)

data class RegionModel(
        val id: Int = INT_ZERO,
        val name: String = STRING_EMPTY
) : EmaModel