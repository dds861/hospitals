package com.dd.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.dd.data.db.entities.HospitalDbData

@Dao
interface HospitalDbDao : BaseDbDao<HospitalDbData> {
    @Query("SELECT * FROM ${HospitalDbData.TABLE_NAME}")
    fun getAllHospitals(): List<HospitalDbData>

    @Query("SELECT * FROM ${HospitalDbData.TABLE_NAME} WHERE ${HospitalDbData.CATEGORY_ID}=:sectionId AND ${HospitalDbData.REGION_ID}=:regionId")
    fun getHospitalsBySectionIdAndRegionId(sectionId: Int, regionId: Int): List<HospitalDbData>

    @Query("SELECT * FROM ${HospitalDbData.TABLE_NAME} WHERE ${HospitalDbData.REGION_ID}=:categoryId")
    fun getHospitalsBySectionId(categoryId: Int): List<HospitalDbData>

    @Query("SELECT * FROM ${HospitalDbData.TABLE_NAME} WHERE ${HospitalDbData.REGION} LIKE '%' || :queryText|| '%' OR ${HospitalDbData.BRANCH} LIKE '%' || :queryText|| '%' OR ${HospitalDbData.ADDRESS} LIKE '%' || :queryText|| '%' OR ${HospitalDbData.PHONE} LIKE '%' || :queryText|| '%'")
    fun getHospitalsByQueryText(queryText: String): List<HospitalDbData>

    @Query("SELECT ${HospitalDbData.REGION} FROM ${HospitalDbData.TABLE_NAME} ORDER BY RANDOM() LIMIT 1")
    fun getRandomHospital(): String
}