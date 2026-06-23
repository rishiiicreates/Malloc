package com.passby.malloc.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "products",
    foreignKeys = [
        ForeignKey(
            entity = VendorEntity::class,
            parentColumns = ["id"],
            childColumns = ["vendorId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [Index("vendorId")],
)
data class ProductEntity(
    @PrimaryKey val id: String,
    val vendorId: String,
    val name: String,
    val description: String,
    /** Price in paise. */
    val price: Long,
    /** Optional discounted price in paise. */
    val offerPrice: Long? = null,
    val images: List<String> = emptyList(),
    val stock: Int = 0,
    val isAvailable: Boolean = true,
)

