package com.dd.domain.model

import com.carmabs.ema.core.constants.INT_ZERO
import com.carmabs.ema.core.constants.STRING_EMPTY
import com.carmabs.ema.core.state.EmaModel

data class ResponseHospitalModel(
        val result: String = STRING_EMPTY,
        val randomHospital: String = STRING_EMPTY,
        val list: List<HospitalModel> = listOf()
)

data class HospitalModel(
        val id: Int = INT_ZERO,
        val region_id: Int = INT_ZERO,
        val region: String = STRING_EMPTY,
        val category_id: Int = INT_ZERO,
        val category: String = STRING_EMPTY,
        val branch: String = STRING_EMPTY,
        val phone: String? = STRING_EMPTY,
        val address: String? = STRING_EMPTY
) : EmaModel
