package com.dd.domain.usecase

import com.carmabs.ema.core.usecase.EmaUseCase
import com.dd.domain.model.RequestRegionModel
import com.dd.domain.model.ResponseRegionModel
import com.dd.domain.repository.LocalStorageRepository

class GetLocalRegionUseCase(private val repository: LocalStorageRepository)
    : EmaUseCase<RequestRegionModel, ResponseRegionModel>() {

    override suspend fun useCaseFunction(input: RequestRegionModel): ResponseRegionModel {
        return repository.getAllRegions(input)
    }
}


