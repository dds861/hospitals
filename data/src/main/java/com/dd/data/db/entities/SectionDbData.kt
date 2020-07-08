package com.dd.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = SectionDbData.TABLE_NAME)
data class SectionDbData(
        @PrimaryKey
        @ColumnInfo(name = ID) val id: Int,
        @ColumnInfo(name = NAME) val name: String
) {

    //////////////////////////TABLE///////////////////////////

    companion object {
        const val TABLE_NAME = "category"
        const val ID = "id"
        const val NAME = "name"
    }

    //////////////////////////////////////////////////////////

}