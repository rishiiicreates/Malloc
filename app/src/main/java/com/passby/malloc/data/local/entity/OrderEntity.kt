package com.passby.malloc.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "orders",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["customerId"],
        ),
        ForeignKey(
            entity = VendorEntity::class,
            parentColumns = ["id"],
            childColumns = ["vendorId"],
        ),
    ],
    indices = [Index("customerId"), Index("vendorId"), Index("status")],
)
data class OrderEntity(
    @PrimaryKey val id: String,
    val customerId: String,
    val vendorId: String,
    /** Total charged to the customer in paise. */
    val totalAmount: Long,
    /** Eight-percent PassBy fee in paise. */
    val platformFee: Long,
    /** Amount owed to the vendor in paise. */
    val vendorAmount: Long,
    val status: OrderStatus = OrderStatus.PENDING,
    val paymentStatus: PaymentStatus = PaymentStatus.PENDING,
    val pickupType: PickupType,
    val orderPin: String,
)

