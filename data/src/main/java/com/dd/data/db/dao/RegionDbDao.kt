package com.dd.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.dd.data.db.entities.RegionDbData


@Dao
interface RegionDbDao : BaseDbDao<RegionDbData> {

    @Query("SELECT * FROM ${RegionDbData.TABLE_NAME}")
    fun getAllRegions(): List<RegionDbData>
}