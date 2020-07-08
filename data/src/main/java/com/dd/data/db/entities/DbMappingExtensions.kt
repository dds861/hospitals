package com.dd.data.db.entities

import com.dd.domain.model.*

fun List<RegionDbData>.toDomainModel(): ResponseRegionModel {
    return ResponseRegionModel(
            list = this.map {
                RegionModel(
                        id = it.id,
                        name = it.name)
            }
    )
}

fun List<SectionDbData>.toDomainModel(): ResponseSectionModel {
    return ResponseSectionModel(
            list = this.map {
                SectionModel(
                        id = it.id,
                        name = it.name)
            }
    )
}

fun List<HospitalDbData>.toDomainModel(): ResponseHospitalModel {
    return ResponseHospitalModel(
            list = this.map {
                HospitalModel(
                        id = it.id,
                        region_id = it.region_id,
                        region = it.region,
                        category_id = it.category_id,
                        category = it.category,
                        branch = it.branch,
                        phone = it.phone,
                        address = it.address
                )
            }
    )
}

fun String.toDomainModel(): ResponseHospitalModel {
    return ResponseHospitalModel(
            randomHospital = this.replace("\\\\n".toRegex(), "\n")
    )
}
