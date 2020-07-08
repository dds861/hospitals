package com.dd.domain.usecase

import com.carmabs.ema.core.usecase.EmaUseCase
import com.dd.domain.model.RequestHospitalModel
import com.dd.domain.model.ResponseHospitalModel
import com.dd.domain.repository.Repository

class GetHospitalUseCase(private val repository: Repository)
    : EmaUseCase<RequestHospitalModel, ResponseHospitalModel>() {

    override suspend fun useCaseFunction(input: RequestHospitalModel): ResponseHospitalModel {
        return repository.getHospital(input)
    }
}