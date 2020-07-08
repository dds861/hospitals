package com.dd.data.repository

import com.dd.domain.model.RequestHospitalModel
import com.dd.domain.model.ResponseHospitalModel
import com.dd.domain.model.RequestRegionModel
import com.dd.domain.model.ResponseRegionModel
import com.dd.domain.repository.Repository

class CacheApiRepository(private val repository: Repository) : Repository {

    override suspend fun getRegion(requestRegionModel: RequestRegionModel): ResponseRegionModel {
        return repository.getRegion(requestRegionModel)
    }

    override suspend fun getHospital(requestHospitalModel: RequestHospitalModel): ResponseHospitalModel {
        return repository.getHospital(requestHospitalModel)
    }
}

