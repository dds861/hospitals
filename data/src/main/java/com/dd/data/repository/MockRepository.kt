package com.dd.data.repository


import com.dd.domain.model.RequestHospitalModel
import com.dd.domain.model.ResponseHospitalModel
import com.dd.domain.model.RequestRegionModel
import com.dd.domain.model.ResponseRegionModel
import com.dd.domain.repository.Repository

class MockRepository : Repository {

    override suspend fun getRegion(requestRegionModel: RequestRegionModel): ResponseRegionModel {
        return ResponseRegionModel(
                result = "AnyText"
        )
    }

    override suspend fun getHospital(requestHospitalModel: RequestHospitalModel): ResponseHospitalModel {
        return ResponseHospitalModel(
                result = "AnyText"
        )
    }
}