package com.dd.domain.model

import com.carmabs.ema.core.constants.INT_ZERO
import com.carmabs.ema.core.constants.STRING_EMPTY

data class RequestHospitalModel(
        val default: String = STRING_EMPTY,
        val hospitalId: Int = INT_ZERO,
        val queryText: String = STRING_EMPTY
)