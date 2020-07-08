package com.dd.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = RegionDbData.TABLE_NAME)
data class RegionDbData(
        @PrimaryKey
        @ColumnInfo(name = ID) val id: Long,
        @ColumnInfo(name = REGION_NAME) val category_text: String
) {

    //////////////////////////TABLE///////////////////////////

    companion object {
        const val TABLE_NAME = "regions"
        const val ID = "id"
        const val REGION_NAME = "name"
    }

    //////////////////////////////////////////////////////////

}