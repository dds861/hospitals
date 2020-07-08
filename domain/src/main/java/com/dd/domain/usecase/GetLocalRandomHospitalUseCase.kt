package com.dd.domain.usecase

import com.carmabs.ema.core.usecase.EmaUseCase
import com.dd.domain.model.ResponseHospitalModel
import com.dd.domain.repository.LocalStorageRepository

class GetLocalRandomHospitalUseCase(private val repository: LocalStorageRepository)
    : EmaUseCase<Unit, ResponseHospitalModel>() {
    override suspend fun useCaseFunction(input: Unit): ResponseHospitalModel {
        return repository.getRandomHospital()
    }
}