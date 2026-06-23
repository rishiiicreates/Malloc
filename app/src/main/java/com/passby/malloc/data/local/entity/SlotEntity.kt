package com.passby.malloc.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "slots",
    foreignKeys = [
        ForeignKey(
            entity = VendorEntity::class,
            parentColumns = ["id"],
            childColumns = ["vendorId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index("vendorId"),
        Index(value = ["vendorId", "date", "startTime", "endTime"], unique = true),
    ],
)
data class SlotEntity(
    @PrimaryKey val id: String,
    val vendorId: String,
    /** ISO-8601 local date, for example 2026-06-22. */
    val date: String,
    /** ISO-8601 local time, for example 14:30. */
    val startTime: String,
    val endTime: String,
    val maxOrders: Int,
    val booked: Int = 0,
    val isBlocked: Boolean = false,
)

