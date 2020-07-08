package com.dd.data.db.entities

import com.dd.domain.model.*

fun List<RegionDbData>.toDomainModel(): ResponseRegionModel {
    return ResponseRegionModel(
            list = this.map { RegionModel(
                    id = it.id.toInt(),
                    name = it.category_text) }
    )
}

fun List<SectionDbData>.toDomainModel(): ResponseSectionModel {
    return ResponseSectionModel(
            list = this.map { SectionModel(
                    id = it.id.toInt(),
                    name = it.name) }
    )
}

fun List<HospitalDbData>.toDomainModel(): ResponseHospitalModel {
    return ResponseHospitalModel(
            list = this.map {
                HospitalModel(
                        id = it.category_id,
                        branch = it.makal_branch,
                        address = it.makal_address,
                        phone = it.makal_phone
                )
            }
    )
}

fun String.toDomainModel(): ResponseHospitalModel {
    return ResponseHospitalModel(
            randomHospital = this.replace("\\\\n".toRegex(), "\n")
    )
}
