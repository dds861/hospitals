package com.dd.data.net.model


import com.dd.domain.model.RequestHospitalModel
import com.dd.domain.model.RequestRegionModel
import com.dd.domain.model.ResponseHospitalModel
import com.dd.domain.model.ResponseRegionModel

fun ResponseCategoryApi.toDomainModel(): ResponseRegionModel = ResponseRegionModel(
        result = this.result
)

fun RequestRegionModel.toDataModel(): RequestCategoryApi = RequestCategoryApi(
        default = this.name
)

fun ResponseMakalApi.toDomainModel(): ResponseHospitalModel = ResponseHospitalModel(
        result = this.result
)

fun RequestHospitalModel.toDataModel(): RequestMakalApi = RequestMakalApi(
        default = this.default
)