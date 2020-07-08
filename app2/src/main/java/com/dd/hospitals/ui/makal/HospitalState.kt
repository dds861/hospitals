package com.dd.hospitals.ui.makal

import com.carmabs.ema.core.constants.INT_ZERO
import com.carmabs.ema.core.constants.STRING_EMPTY
import com.carmabs.ema.core.state.EmaBaseState
import com.dd.domain.model.HospitalModel

data class HospitalState(
        val default: String = STRING_EMPTY,
        val categoryId: Int = INT_ZERO,
        val listHospitals: List<HospitalModel> = listOf(),
        val categoryTitle: String = STRING_EMPTY
) : EmaBaseState