package com.dd.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = HospitalDbData.TABLE_NAME)
data class HospitalDbData(

        @PrimaryKey
        @ColumnInfo(name = ID) val id: Long,
        @ColumnInfo(name = REGION_ID) val category_id: Int,
        @ColumnInfo(name = CATEGORY_ID) val makal_sort_id: Int,
        @ColumnInfo(name = REGION) val makal_text: String,
        @ColumnInfo(name = CATEGORY) val makal_branch: String,
        @ColumnInfo(name = BRANCH) val makal_person: String?,
        @ColumnInfo(name = PHONE) val makal_phone: String?,
        @ColumnInfo(name = ADDRESS) val makal_address: String?
) {

    //////////////////////////TABLE///////////////////////////

    companion object {
        const val TABLE_NAME = "hospitals"
        const val ID = "id"
        const val REGION_ID = "region_id"
        const val CATEGORY_ID = "category_id"
        const val REGION = "region"
        const val CATEGORY = "category"
        const val BRANCH = "branch"
        const val PHONE = "phone"
        const val ADDRESS = "address"
    }
    //////////////////////////////////////////////////////////

}