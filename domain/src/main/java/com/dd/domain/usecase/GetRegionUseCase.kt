package com.dd.domain.usecase

import com.carmabs.ema.core.usecase.EmaUseCase
import com.dd.domain.model.RequestRegionModel
import com.dd.domain.model.ResponseRegionModel
import com.dd.domain.repository.Repository

class GetRegionUseCase(private val repository: Repository)
    : EmaUseCase<RequestRegionModel, ResponseRegionModel>() {

    override suspend fun useCaseFunction(input: RequestRegionModel): ResponseRegionModel {
        return repository.getRegion(input)
    }
}


