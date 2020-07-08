package com.dd.domain.usecase

import com.carmabs.ema.core.usecase.EmaUseCase
import com.dd.domain.model.RequestSectionModel
import com.dd.domain.model.ResponseSectionModel
import com.dd.domain.repository.LocalStorageRepository

class GetLocalSectionUseCase(private val repository: LocalStorageRepository)
    : EmaUseCase<RequestSectionModel, ResponseSectionModel>() {

    override suspend fun useCaseFunction(input: RequestSectionModel): ResponseSectionModel {
        return repository.getAllSections(input)
    }
}


