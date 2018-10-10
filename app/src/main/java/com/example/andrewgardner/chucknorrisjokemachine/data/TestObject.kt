/*
 * Copyright (c) 2018. START TODAY Engineering CO.,LTD. All rights reserved.
 */

package com.zozo.pb.repo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.Calendar
import java.util.Calendar.DAY_OF_YEAR

@Entity(tableName = "objects")
data class TestObject(
    @PrimaryKey @ColumnInfo(name = "id") val objectId: String,
    val name: String,
    val description: String,
    val dateIndex: Int = 7,
    val imageUrl: String = ""
) {

    fun changeDate(lastWateringDate: Calendar) =
            Calendar.getInstance() > lastWateringDate.apply { add(DAY_OF_YEAR, dateIndex) }

    override fun toString() = name
}
