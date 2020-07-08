package com.dd.domain.repository

import com.dd.domain.model.*

interface LocalStorageRepository {
    fun getAllRegions(request: RequestRegionModel): ResponseRegionModel

    fun getAllSections(request: RequestSectionModel): ResponseSectionModel

    fun getAllHospitals(request: RequestHospitalModel): ResponseHospitalModel

    fun getHospitalsByCategoryId(request: RequestHospitalModel): ResponseHospitalModel

    fun getHospitalsByQueryText(request: RequestHospitalModel): ResponseHospitalModel

    fun getRandomHospital(): ResponseHospitalModel
}