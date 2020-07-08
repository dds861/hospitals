package com.dd.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.dd.data.db.entities.SectionDbData

@Dao
interface SectionDbDao : BaseDbDao<SectionDbData> {

    @Query("SELECT * FROM ${SectionDbData.TABLE_NAME}")
    fun getAllSections(): List<SectionDbData>
}