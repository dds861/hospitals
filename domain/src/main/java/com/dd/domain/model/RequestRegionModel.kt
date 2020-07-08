package com.dd.domain.model

import com.carmabs.ema.core.constants.INT_ZERO
import com.carmabs.ema.core.constants.STRING_EMPTY

data class RequestRegionModel(
        val id: Int = INT_ZERO,
        val name: String = STRING_EMPTY
)