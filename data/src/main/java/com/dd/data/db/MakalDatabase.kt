package com.dd.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dd.data.db.dao.HospitalDbDao
import com.dd.data.db.dao.RegionDbDao
import com.dd.data.db.dao.SectionDbDao
import com.dd.data.db.entities.HospitalDbData
import com.dd.data.db.entities.RegionDbData
import com.dd.data.db.entities.SectionDbData

@Database(
        entities = [
            RegionDbData::class,
            SectionDbData::class,
            HospitalDbData::class
        ],
        version = 1,
        exportSchema = false
)
abstract class MakalDatabase : RoomDatabase() {
    abstract fun regionDao(): RegionDbDao
    abstract fun hospitalDao(): HospitalDbDao
    abstract fun sectionDao(): SectionDbDao
}