package com.dd.domain.repository

import com.dd.domain.model.RequestHospitalModel
import com.dd.domain.model.RequestRegionModel
import com.dd.domain.model.ResponseHospitalModel
import com.dd.domain.model.ResponseRegionModel


interface Repository {

    suspend fun getRegion(requestRegionModel: RequestRegionModel): ResponseRegionModel

    suspend fun getHospital(requestHospitalModel: RequestHospitalModel): ResponseHospitalModel
}