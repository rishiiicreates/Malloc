package com.passby.malloc.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "vendors",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [Index(value = ["userId"], unique = true)],
)
data class VendorEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val businessName: String,
    val category: String,
    val description: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val isOpen: Boolean = false,
    val payoutMethod: PayoutMethod? = null,
    val verificationStatus: VerificationStatus = VerificationStatus.PENDING,
    val rejectionReason: String? = null,
)

