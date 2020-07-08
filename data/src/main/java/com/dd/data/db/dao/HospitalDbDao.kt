package com.dd.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.dd.data.db.entities.HospitalDbData

@Dao
interface HospitalDbDao : BaseDbDao<HospitalDbData> {
    @Query("SELECT * FROM ${HospitalDbData.TABLE_NAME}")
    fun getAllHospitals(): List<HospitalDbData>

    @Query("SELECT * FROM ${HospitalDbData.TABLE_NAME} WHERE ${HospitalDbData.REGION_ID}=:categoryId")
    fun getHospitalsByCategoryId(categoryId: Int): List<HospitalDbData>

    @Query("SELECT * FROM ${HospitalDbData.TABLE_NAME} WHERE ${HospitalDbData.ADDRESS} LIKE '%' || :queryText|| '%'")
    fun getHospitalsByQueryText(queryText: String): List<HospitalDbData>

    @Query("SELECT ${HospitalDbData.REGION} FROM ${HospitalDbData.TABLE_NAME} ORDER BY RANDOM() LIMIT 1")
    fun getRandomHospital(): String
}