package com.dd.hospitals.ui.hospital

import com.carmabs.ema.core.constants.INT_ZERO
import com.carmabs.ema.core.constants.STRING_EMPTY
import com.carmabs.ema.core.state.EmaBaseState
import com.dd.domain.model.HospitalModel

data class HospitalState(
        val default: String = STRING_EMPTY,
        val sectionId: Int = INT_ZERO,
        val regionId: Int = INT_ZERO,
        val listHospitals: List<HospitalModel> = listOf(),
        val regionTitle: String = STRING_EMPTY
) : EmaBaseState